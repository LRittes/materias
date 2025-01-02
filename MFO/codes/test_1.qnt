module test_1 {
    var g: int
    var p: int

    action enche_g  = all {
    g' = 5,
    p' = p,
  }

    action enche_p = all {
    g' = g,
    p' = 3,
  }

    action esvazia_g = all {
    g' = 0,
    p' = p,
  }

    action esvazia_p = all {
    g' = g,
    p' = 0,
  }

    action g_to_p = {
    if (p + g <= 3) all {
      p' = p + g,
      g' = 0,
    } else all {
      p' = 3,
      g' = p - (3 - g),
    }
  }
    action p_to_g  = {
    if (p + g <= 5) all {
      g' = p + g,
      p' = 0,
    } else all {
      g' = 5,
      p' = g - (5 - p),
    }
  }

    action init = all {
        g' = 0,
        p' = 0,
    }

action step = any {
    enche_g,
    enche_p ,
    esvazia_g ,
    esvazia_p ,
    g_to_p ,
    p_to_g ,
}

val inv = g != 4

run solution = 
    init
        .then(enche_g)
        .then(g_to_p)
        .then(esvazia_p)
        .then(g_to_p)
        .then(enche_g)
        .then(g_to_p)
        .expect(g == 4)
}