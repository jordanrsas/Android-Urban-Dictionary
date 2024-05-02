package com.cjra.urbandictionary.application.presentation

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain

class HomeStateMapper : Function1<Result<List<DefinitionPlain>>, HomeState> {

    override fun invoke(data: Result<List<DefinitionPlain>>): HomeState {
        return when {
            data.isSuccess -> {
                val definitionsPlain = data.getOrNull()
                if (definitionsPlain.isNullOrEmpty()) {
                    HomeState.Empty
                } else {
                    HomeState.Success(Definition.mapDefinitionsFromData(definitionsPlain))
                }
            }

            data.isFailure -> {
                HomeState.Error
            }

            else -> {
                // Emit loading state when result is still loading
                HomeState.Loading
            }
        }
    }
}