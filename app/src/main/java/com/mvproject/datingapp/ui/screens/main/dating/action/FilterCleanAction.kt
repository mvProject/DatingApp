/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.action

sealed class FilterCleanAction {
    data object CleanAll : FilterCleanAction()
    data object CleanInterest : FilterCleanAction()
    data object CleanOrientation : FilterCleanAction()
    data object CleanMarital : FilterCleanAction()
    data object CleanChildren : FilterCleanAction()
    data object CleanHeight : FilterCleanAction()
    data object CleanZodiac : FilterCleanAction()
    data object CleanAlcohol : FilterCleanAction()
    data object CleanSmoke : FilterCleanAction()
    data object CleanCharacter : FilterCleanAction()
    data object CleanReligion : FilterCleanAction()
    data object CleanLanguages : FilterCleanAction()
    data object CleanPets : FilterCleanAction()
}