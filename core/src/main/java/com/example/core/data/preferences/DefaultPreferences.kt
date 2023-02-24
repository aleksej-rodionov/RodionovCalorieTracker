package com.example.core.data.preferences

import android.content.SharedPreferences
import android.util.Log
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.preferences.Preferences.Companion.KEY_ACTIVITY_LEVEL
import com.example.core.domain.preferences.Preferences.Companion.KEY_AGE
import com.example.core.domain.preferences.Preferences.Companion.KEY_CARB_RATIO
import com.example.core.domain.preferences.Preferences.Companion.KEY_FAT_RATIO
import com.example.core.domain.preferences.Preferences.Companion.KEY_GENDER
import com.example.core.domain.preferences.Preferences.Companion.KEY_GOAL_TYPE
import com.example.core.domain.preferences.Preferences.Companion.KEY_HEIGHT
import com.example.core.domain.preferences.Preferences.Companion.KEY_PROTEIN_RATIO
import com.example.core.domain.preferences.Preferences.Companion.KEY_SHOULD_SHOW_ONBOARDING
import com.example.core.domain.preferences.Preferences.Companion.KEY_WEIGHT

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {

    override fun saveGender(gender: Gender) {
        sharedPref.edit().putString(KEY_GENDER, gender.name).apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit().putInt(KEY_AGE, age).apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit().putFloat(KEY_WEIGHT, weight).apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit().putInt(KEY_HEIGHT, height).apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit().putString(KEY_ACTIVITY_LEVEL, level.name).apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPref.edit().putString(KEY_GOAL_TYPE, type.name).apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit().putFloat(KEY_CARB_RATIO, ratio).apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit().putFloat(KEY_PROTEIN_RATIO, ratio).apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit().putFloat(KEY_FAT_RATIO, ratio).apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(KEY_AGE, 32)
        val height = sharedPref.getInt(KEY_HEIGHT, 177)
        val weight = sharedPref.getFloat(KEY_WEIGHT, 65f)
        val genderString = sharedPref.getString(KEY_GENDER, "male")
        val activityLevelString = sharedPref.getString(KEY_ACTIVITY_LEVEL, "medium")
        val goalType = sharedPref.getString(KEY_GOAL_TYPE, "keep_weight")
        val carbRatio = sharedPref.getFloat(KEY_CARB_RATIO, 0.4f)
        val proteinRatio = sharedPref.getFloat(KEY_PROTEIN_RATIO, 0.3f)
        val fatRatio = sharedPref.getFloat(KEY_FAT_RATIO, 0.3f)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }


    override fun saveShouldShowOnboarding(should: Boolean) {
        sharedPref.edit().putBoolean(KEY_SHOULD_SHOW_ONBOARDING, should).apply()
    }

    override fun fetchShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(KEY_SHOULD_SHOW_ONBOARDING, true)
    }
}