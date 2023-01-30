package cc.trixey.invero.script.kether

import cc.trixey.invero.core.util.KetherHandler
import taboolib.module.kether.KetherParser
import taboolib.module.kether.combinationParser

/**
 * Invero
 * cc.trixey.invero.script.kether.ActionSource
 *
 * @author Arasple
 * @since 2023/1/29 22:47
 */
object ActionSource {

    @KetherParser(["element"], namespace = "invero", shared = true)
    fun parser() = combinationParser {
        it.group(text()).apply(it) { key ->
            now {
                val value = selfSourceObject()[key]
                if (value?.startsWith("ext.kether:") == true) {
                    val source = value.removePrefix("ext.kether:")
                    KetherHandler.invoke(source, player(), variables().toMap()).getNow("<TIMEOUT>")
                } else {
                    value
                }
            }
        }
    }

}