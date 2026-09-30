package seg3x02.converter

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalculatorController {
    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("error", "")
        model.addAttribute("num1", "")
        model.addAttribute("num2", "")
        model.addAttribute("result", "")
    }

    @GetMapping("/calculator")
    fun calculatorHome(): String {
        return "calculator"
    }

    @GetMapping(value = ["/calculate"])
    fun doCalculate(
        @RequestParam(value = "num1", required = false) num1: String,
        @RequestParam(value = "num2", required = false) num2: String,
        @RequestParam(value = "operation", required = false) operation: String,
        model: Model
    ): String {
        try {
            val val1 = num1.toDouble()
            val val2 = num2.toDouble()
            val result: Double = when (operation) {
                "+" -> val1 + val2
                "-" -> val1 - val2
                "*" -> val1 * val2
                "/" -> val1 / val2
                else -> {
                    model.addAttribute("error", "OperationFormatError")
                    model.addAttribute("num1", num1)
                    model.addAttribute("num2", num2)
                    return "calculator"
                }
            }
            model.addAttribute("num1", num1)
            model.addAttribute("num2", num2)
            model.addAttribute("result", String.format("%.2f", result))
        } catch (exp: NumberFormatException) {
            model.addAttribute("error", "NumberFormatError")
            model.addAttribute("num1", num1)
            model.addAttribute("num2", num2)
        }
        return "calculator"
    }
}