import com.avaquo.starry.api.StarryEntity
import com.avaquo.starry.api.StarryWorld
import com.avaquo.starry.queries.Filter
import com.avaquo.starry.queries.Query
import com.avaquo.starry.queries.Term
import com.avaquo.starry.world.World
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.time.Month

class QueryTest {
    private val component1 = TestComponent(1, 1)
    private val component2 = TestComponent2(Month.AUGUST)

    @Nested
    inner class Filters {
        private val world = World("TestWorld", false)
        private val storage = world.storage

        @Test
        fun `Find an Entity in a Filter`() {
            val e = world.createEntity("TestEntity")
            world.addElementToEntity(e.id, component1)

            val type = storage.getType("TestComponent")
            val filter = Filter(storage, mutableListOf(Term(type)))

            val entitiesFound = filter.search()

            Assertions.assertAll(
                Executable { Assertions.assertEquals(1, entitiesFound.size) },
                Executable { Assertions.assertEquals(e.id, entitiesFound[0]) },
            )

            world.destroyEntity(e.id)
        }

        @Test
        fun `Find multiple Entities in a Filter`() {
            val e1 = world.createEntity("TestEntity01")
            val e2 = world.createEntity("TestEntity02")
            val e3 = world.createEntity("TestEntity03")

            world.addElementToEntity(e1.id, component1)
            world.addElementToEntity(e2.id, component1)
            world.addElementToEntity(e3.id, TestComponent2(Month.AUGUST))

            val type = storage.getType("TestComponent")
            val filter = Filter(storage, mutableListOf(Term(type)))

            val entitiesFound = filter.search()

            Assertions.assertAll(
                Executable { Assertions.assertEquals(2, entitiesFound.size) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e1.id)) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e2.id)) },
                Executable { Assertions.assertFalse(entitiesFound.contains(e3.id)) }
            )

            world.destroyEntity(e1.id)
            world.destroyEntity(e2.id)
            world.destroyEntity(e3.id)
        }

        @Test
        fun `Find multiple Elements in a single Filter`() {
            val e1 = world.createEntity("TestEntity01")
            val e2 = world.createEntity("TestEntity02")
            val e3 = world.createEntity("TestEntity03")

            world.addElementToEntity(e1.id, component1)
            world.addElementToEntity(e1.id, component2)

            val type1 = storage.getType("TestComponent")
            val type2 = storage.getType("TestComponent2")

            val filter = Filter(storage, mutableListOf(Term(type1), Term(type2)))

            val entitiesFound = filter.search()

            Assertions.assertAll(
                Executable { Assertions.assertEquals(1, entitiesFound.size) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e1.id)) }
            )

            world.destroyEntity(e1.id)
            world.destroyEntity(e2.id)
            world.destroyEntity(e3.id)
        }

        @Test
        fun `Find Entities with Specific Elements`() {
            val e1 = world.createEntity("TestEntity01")
            val e2 = world.createEntity("TestEntity02")

            world.addElementToEntity(e1.id, component1)

            val type1 = storage.getType("TestComponent")

            val filter1 = Filter(storage, mutableListOf(Term(type1, component1)))

            val entitiesFound1 = filter1.search()

            Assertions.assertAll(
                Executable { Assertions.assertEquals(1, entitiesFound1.size) },
                Executable { Assertions.assertTrue(entitiesFound1.contains(e1.id)) }
            )

            world.destroyEntity(e1.id)
            world.destroyEntity(e2.id)
        }
    }

    @Nested
    inner class Queries {
        private val world = World("TestWorld", false)
        private val storage = world.storage

        @Test
        fun `Cache Results from a Query`() {
            val e1 = world.createEntity("TestEntity01")
            val e2 = world.createEntity("TestEntity02")
            val e3 = world.createEntity("TestEntity03")

            world.addElementToEntity(e1.id, component1)
            world.addElementToEntity(e2.id, component1)
            world.addElementToEntity(e3.id, component2)

            val type = storage.getType("TestComponent")
            val query = Query(storage, Filter(storage, mutableListOf(Term(type))))

            query.searchAndCache()

            val entitiesFound = query.cache

            Assertions.assertAll(
                Executable { Assertions.assertEquals(2, entitiesFound.size) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e1.id)) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e2.id)) },
                Executable { Assertions.assertFalse(entitiesFound.contains(e3.id)) }
            )

            world.destroyEntity(e1.id)
            world.destroyEntity(e2.id)
            world.destroyEntity(e3.id)
        }

        @Test
        fun `Refresh Cache in a Query`() {
            val e1 = world.createEntity("TestEntity01")
            val e2 = world.createEntity("TestEntity02")

            world.addElementToEntity(e1.id, component1)
            world.addElementToEntity(e2.id, component1)

            val type = storage.getType("TestComponent")
            val query = Query(storage, Filter(storage, mutableListOf(Term(type))))

            query.searchAndCache()

            val entitiesFound = query.cache

            Assertions.assertAll(
                Executable { Assertions.assertEquals(2, entitiesFound.size) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e1.id)) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e2.id)) },
            )

            val e3 = world.createEntity("TestEntity03")

            world.addElementToEntity(e3.id, component1)

            query.searchAndCache()

            Assertions.assertAll(
                Executable { Assertions.assertEquals(3, entitiesFound.size) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e1.id)) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e2.id)) },
                Executable { Assertions.assertTrue(entitiesFound.contains(e3.id)) }
            )

            world.destroyEntity(e1.id)
            world.destroyEntity(e2.id)
            world.destroyEntity(e3.id)
        }
    }

    @Nested
    inner class QueriesAPI {
        private val world = StarryWorld("TestWorld", false)
        private val storage = world.world.storage

        @Test
        fun `Create a Filter with the API`() {
            val e1 = StarryEntity(world, "TestEntity01", listOf(component1))
            val e2 = StarryEntity(world, "TestEntity02", listOf(component1))
            val e3 = StarryEntity(world, "TestEntity03")

            // TODO: API tests
        }
    }
}