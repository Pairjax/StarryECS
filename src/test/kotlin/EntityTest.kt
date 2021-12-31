import com.avaquo.starry.entities.Entity
import com.avaquo.starry.world.World
import org.junit.jupiter.api.*
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

class EntityTest {

    private val world = World("TestWorld", false)
    private val storage = world.storage

    @Nested
    inner class SingleEntities {

            @Test
            fun `Create An Entity`() {
                val entity = world.createEntity("TestEntity")
                val entityID = entity.id

                val entityWorldID = storage.getEntity(entityID).id

                Assertions.assertAll(
                    Executable { Assertions.assertEquals(0UL, entityID) },
                    Executable { Assertions.assertEquals(entityWorldID, entityID) },
                    Executable { Assertions.assertEquals(1UL, storage.typeMap[entity::class.simpleName]) },
                    Executable { Assertions.assertEquals(entity.type, storage.getEntity(storage.tables[0][0]).type) },
                    Executable { Assertions.assertEquals(entity, storage.getEntity(storage.tables[0][0])) },
                )
            }

            @Test
            fun `Add an Element to the Entity, then Remove it`() {
                val entity = world.createEntity("TestEntity")
                val entityID = entity.id

                val username = "User01"

                world.addElementToEntity(entity.id, username)

                Assertions.assertAll(
                    Executable { Assertions.assertEquals(storage.getElement(entity.ids[0]), username) },
                    Executable { Assertions.assertEquals(1, entity.ids.size) },
                )

                val elementID = entity.ids[0]

                world.removeElementFromEntity(entity.id, elementID)

                Assertions.assertAll(
                    Executable { Assertions.assertFalse(storage.getElement(elementID) as Boolean) },
                    Executable { Assertions.assertEquals(0, entity.ids.size) },
                )
            }

            @Test
            fun `Remove an Entity from the World`() {
                val entity = world.createEntity("TestEntity")
                val entityID = entity.id

                val username = "User03"

                world.addElementToEntity(entity.id, username)

                Assertions.assertEquals(storage.getElement(entity.ids[0]), username)

                world.destroyEntity(entity.id)

                Assertions.assertFalse(storage.getElement(entity.id) as Boolean)
            }
        }

        @Nested
        inner class BulkEntities {
            @Test
            fun `Create 1,000 Entities`() {
                val entityList = mutableListOf(world.createEntity("Entity_0"))

                for (i in 1..999) entityList += world.createEntity("Entity_$i")

                for (e in entityList) {
                    Assertions.assertAll(
                        Executable { Assertions.assertEquals(e.id, storage.getEntity(e.id).id) },
                        Executable { Assertions.assertEquals(e, storage.getEntity(e.id)) },
                        Executable { Assertions.assertEquals(e.type, storage.getEntity(storage.tables[0][0]).type) },
                        Executable { Assertions.assertTrue(storage.tables[0].contains(e.id)) },
                    )
                }
            }

            @Test
            fun `Add Elements To Entities`() {
                val entityList = mutableListOf(world.createEntity("Entity_0"))

                for (i in 1..999) entityList += world.createEntity("Entity_$i")

                for (i in 0..999) world.addElementToEntity(entityList[i].id, i)

                for (i in 0..999) Assertions.assertEquals(storage.getElement(entityList[i].ids[0]), i)
            }

            @Test
            fun `Remove All Entities`() {
                val entityList = mutableListOf(world.createEntity("Entity_0"))

                for (i in 1..999) entityList += world.createEntity("Entity_$i")

                val idList = entityList.map { it.id }

                for (id in idList) {
                    world.destroyEntity(id)
                }

                for (id in idList) Assertions.assertFalse(storage.getElement(id) as Boolean)
            }
        }
}