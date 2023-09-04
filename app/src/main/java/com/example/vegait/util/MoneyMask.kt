package com.example.vegait.util

import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher

fun moneyMask(): ValorMonetarioWatcher {
    return ValorMonetarioWatcher.Builder()
        .comMantemZerosAoLimpar()
        .comSimboloReal()
        .build()
}