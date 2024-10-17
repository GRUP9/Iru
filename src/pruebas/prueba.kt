package pruebas

import java.util.Scanner

fun main() {
    val perro = Dog(1, "Roberto", "Canino", 20.00)
    val gato = Cat(2, "GatoConBotas", "Felino", 15.00)
    val pajaro = Bird(3, "Piolin", "Aviario", 5.00)
    val pajarito = Bird(4,"antonia","Aviario",30.00)

    val listaAnimal = mutableListOf<Animal>()

    listaAnimal.add(perro)
    listaAnimal.add(gato)
    listaAnimal.add(pajaro)

    val petShop = PetShop(listaAnimal)

    perro.ruidos()
    gato.ruidos()
    pajaro.ruidos()

    petShop.removeAnimal(1)
    petShop.findAnimal(2)
    petShop.listAnimal()
    petShop.calculateTotalValue()
    petShop.filtarEspecies("Felino")
    petShop.muestraAnimales()
    petShop.calcularPrecioPromedio()

    val scanner = Scanner(System.`in`)
    println("Ingrese su nombre:")
    val nombre = scanner.nextLine()
    println("Hola, $nombre!")

    while (true){
        println("1. Añadir Animal")
        println("2. Listar Animales")
        println("3. Buscar Animal")
        println("5. Calcular Valor Total")

        val opcion = scanner.nextInt()

        when(opcion){
            1 -> {
                println("Ingrese ID del animal:")
                val id = scanner.nextInt()
                scanner.nextLine()  // Limpiar el buffer
                println("Ingrese nombre del animal:")
                val name = scanner.nextLine()
                println("Ingrese especie del animal:")
                val species = scanner.nextLine()
                println("Ingrese precio del animal:")
                val price = scanner.nextDouble()

                val animal = when (species.lowercase()){
                    "canino" -> Dog(id, name, species, price)
                    "felino" -> Dog(id, name, species, price)
                    "aviario" -> Dog(id, name, species, price)
                    else -> {
                        println("Especie no conocida")
                        Animal(id, name, species, price)
                    }
                }
                petShop.addAnimal(animal)
                println("añadido $name")
            }
            2 -> {
                petShop.listAnimal()
            }
            3 -> {
                println("Ingrese ID del animal a buscar:")
                val id = scanner.nextInt()
                petShop.findAnimal(id)
            }
            4 -> {
                petShop.calculateTotalValue()
            }
            0 -> {
                println("Saliendo...")
                break
            }
            else -> {
                println("Opción no válida. Inténtelo de nuevo.")
            }

        }
    }


}

open class Animal(val id: Int, var name:String, val species:String, val price:Double){

    open fun ruidos(){
        println("$name un ruido")
    }

}

class Dog(id: Int, name: String, species: String, price: Double): Animal(id,name,species,price){
    override fun ruidos(){
        println("$name ladra")
    }
}

class Cat(id: Int, name: String, species: String, price: Double): Animal(id,name,species,price){
    override fun ruidos(){
        println("$name maulla")
    }
}

class Bird(id: Int, name: String, species: String, price: Double): Animal(id,name,species,price){
    override fun ruidos(){
        println("$name canta")
    }
}

class  PetShop(private val listaAnimal: MutableList<Animal>){

    fun addAnimal(animal: Animal){
        listaAnimal.add(animal)
    }

    fun removeAnimal(id: Int) {
        val animal = listaAnimal.find { it.id == id }
        if (animal != null) {
            listaAnimal.remove(animal)
            println("animal con id $id eliminado")
        } else {
            println("no se encontro el animal con id $id")
        }
    }
    fun findAnimal(id: Int){
        val animal= listaAnimal.find { it.id == id }
        if(animal != null){
            println("el animal con ${animal.id} fue encontrado y es un ${animal.species}")
        }else{
            println("el animal no se encontro")
        }
    }
    fun listAnimal() {
        if (listaAnimal.isEmpty()) {
            println("no hay animales en la lista")
        } else {
            println("lista de animales:")
            for (animal in listaAnimal){
                println("${animal.species}")
            }
        }

    }
    fun calculateTotalValue(){
        var total = 0.0
        for (animal in listaAnimal){
            total += animal.price

        }
        println(total)
    }
    fun filtarEspecies(species: String){
        val filtrandoAnimal = listaAnimal.filter { it.species.equals(species, ignoreCase = true) }

        if (filtrandoAnimal.isEmpty()){
            println("no se a encontrado a la especie $species")
        }else{
            println("animales con la especie $species")
            for (animal in filtrandoAnimal){
                println("${animal.name} ${animal.species}")
            }
        }
    }
    fun muestraAnimales(){
        if (listaAnimal.isEmpty()){
            println("no ha lista")
        }else{
            println("lista de animales:")
            for (animal in listaAnimal){
                println("${animal.id} ${animal.name}")
            }
        }
    }
    fun calcularPrecioPromedio() {
        if (listaAnimal.isEmpty()){
            println("no hay prescion en la lista")
        }
        var total = 0.0
        for (animal in listaAnimal){
            total += animal.price
        }
        val promedio = total / listaAnimal.size
        println("el promedio es $promedio")
    }

}

