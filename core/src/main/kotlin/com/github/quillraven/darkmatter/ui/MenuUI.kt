package com.github.quillraven.darkmatter.ui

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.I18NBundle
import ktx.i18n.get
import ktx.scene2d.KTableWidget
import ktx.scene2d.imageButton
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.table
import ktx.scene2d.textButton


private const val OFFSET_TITLE_Y = 15f
private const val MENU_ELEMENT_OFFSET_TITLE_Y = 20f
private const val MENU_DEFAULT_PADDING = 2.5f
private const val MAX_HIGHSCORE_DISPLAYED = 999

class MenuUI(private val bundle: I18NBundle) {
    val table: KTableWidget
    val startGameButton: TextButton
    val soundButton: ImageButton
    val controlButton: TextButton
    private val highScoreButton: TextButton
    val creditsButton: TextButton
    val quitGameButton: TextButton

    init {
        table = scene2d.table {
            defaults().pad(MENU_DEFAULT_PADDING).expandX().fillX().colspan(2)

            label(bundle["gameTitle"], SkinLabel.LARGE.name) { cell ->
                wrap = true
                setAlignment(Align.center)
                cell.apply {
                    padTop(OFFSET_TITLE_Y)
                    padBottom(MENU_ELEMENT_OFFSET_TITLE_Y)
                }
            }
            row()

            startGameButton = textButton(bundle["startGame"], SkinTextButton.DEFAULT.name)
            row()

            soundButton = imageButton(SkinImageButton.SOUND_ON_OFF.name).cell(colspan = 1, expandX = false)
            controlButton = textButton(bundle["control"], SkinTextButton.DEFAULT.name) { cell ->
                cell.colspan(1)
            }
            row()

            highScoreButton = textButton(bundle["highscore", 0], SkinTextButton.LABEL.name)
            row()

            creditsButton = textButton(bundle["credit"], SkinTextButton.DEFAULT.name)
            row()

            quitGameButton = textButton(bundle["quitGame"], SkinTextButton.DEFAULT.name)

            setFillParent(true)
            top()
            pack()
        }
    }

    fun updateHighScore(highScore: Int) {
        highScoreButton.label.run {
            text.setLength(0)
            text.append(bundle["highscore", MathUtils.clamp(highScore, 0, MAX_HIGHSCORE_DISPLAYED)])
            invalidateHierarchy()
        }
    }
}
