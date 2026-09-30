package seg3x02.converter

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class CalculatorControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun request_to_calculator() {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("calculator"))
    }

    @Test
    fun addition() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("num1", "20")
                .param("num2", "8")
                .param("operation", "+"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "28.00"))
            .andExpect(MockMvcResultMatchers.view().name("calculator"))
    }

    @Test
    fun division() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("num1", "10")
                .param("num2", "4")
                .param("operation", "/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "2.50"))
            .andExpect(MockMvcResultMatchers.view().name("calculator"))
    }
}