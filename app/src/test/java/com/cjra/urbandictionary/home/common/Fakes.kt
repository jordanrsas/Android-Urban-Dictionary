package com.cjra.urbandictionary.home.common

import com.cjra.urbandictionary.application.presentation.Definition
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import com.cjra.urbandictionary.framework.remote.DefinitionRaw

val definitionsResultRaw: List<DefinitionRaw> = listOf(
    DefinitionRaw(
        definition = "A new term thats [spreading] across the US like [wildfire].\r\n[Information] means marijuana, and to be informed means to be high.",
        permalink = "http://information.urbanup.com/1995105",
        thumbs_up = 170,
        author = "agilman",
        word = "information",
        defid = 1995105,
        current_vote = "",
        written_on = "2006-09-25T21:11:08.000Z",
        example = "guy 1: \"[Yo dude], wanna [buy] some information?\"\r\nguy 2: \"[No thanks] man, Im already informed\"",
        thumbs_down = 85
    ),
    DefinitionRaw(
        definition = "[Something] that an [average American] [needs more] of.",
        permalink = "http://information.urbanup.com/9506234",
        thumbs_up = 16,
        author = "Hitler the Grammar NAZI",
        word = "information",
        defid = 9506234,
        current_vote = "",
        written_on = "2016-04-03T05:21:06.976Z",
        example = "This [American] is uninformed because they are to [lazy] to [read] the information.",
        thumbs_down = 7
    ),
    DefinitionRaw(
        definition = "To dispense information, as coined by [Harvard] [Professor] [Shoshana] Zuboff.",
        permalink = "http://informate.urbanup.com/3170490",
        thumbs_up = 45,
        author = "millie05",
        word = "informate",
        defid = 3170490,
        current_vote = "",
        written_on = "2008-06-22T00:34:58.000Z",
        example = "\"they'd rather [speculate] before they informate...\" song: [misunderstood] lyrics by [lil wayne]",
        thumbs_down = 5
    )
)

val definitionsPlain: List<DefinitionPlain> = listOf(
    DefinitionPlain(
        definition = "A new term thats [spreading] across the US like [wildfire].\r\n[Information] means marijuana, and to be informed means to be high.",
        likes = 170,
        word = "information",
        example = "guy 1: \"[Yo dude], wanna [buy] some information?\"\r\nguy 2: \"[No thanks] man, Im already informed\"",
        dislikes = 85
    ),
    DefinitionPlain(
        definition = "[Something] that an [average American] [needs more] of.",
        likes = 16,
        word = "information",
        example = "This [American] is uninformed because they are to [lazy] to [read] the information.",
        dislikes = 7
    ),
    DefinitionPlain(
        definition = "To dispense information, as coined by [Harvard] [Professor] [Shoshana] Zuboff.",
        likes = 45,
        word = "informate",
        example = "\"they'd rather [speculate] before they informate...\" song: [misunderstood] lyrics by [lil wayne]",
        dislikes = 5
    )
)

val definitions: List<Definition> = listOf(
    Definition(
        definition = "A new term thats [spreading] across the US like [wildfire].\r\n[Information] means marijuana, and to be informed means to be high.",
        likes = 170,
        word = "information",
        example = "guy 1: \"[Yo dude], wanna [buy] some information?\"\r\nguy 2: \"[No thanks] man, Im already informed\"",
        dislikes = 85
    ),
    Definition(
        definition = "[Something] that an [average American] [needs more] of.",
        likes = 16,
        word = "information",
        example = "This [American] is uninformed because they are to [lazy] to [read] the information.",
        dislikes = 7
    ),
    Definition(
        definition = "To dispense information, as coined by [Harvard] [Professor] [Shoshana] Zuboff.",
        likes = 45,
        word = "informate",
        example = "\"they'd rather [speculate] before they informate...\" song: [misunderstood] lyrics by [lil wayne]",
        dislikes = 5
    )
)