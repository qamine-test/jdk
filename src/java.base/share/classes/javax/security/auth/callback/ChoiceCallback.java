/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.security.buth.cbllbbck;

/**
 * <p> Underlying security services instbntibte bnd pbss b
 * {@code ChoiceCbllbbck} to the {@code hbndle}
 * method of b {@code CbllbbckHbndler} to displby b list of choices
 * bnd to retrieve the selected choice(s).
 *
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 */
public clbss ChoiceCbllbbck implements Cbllbbck, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -3975664071579892167L;

    /**
     * @seribl
     * @since 1.4
     */
    privbte String prompt;
    /**
     * @seribl the list of choices
     * @since 1.4
     */
    privbte String[] choices;
    /**
     * @seribl the choice to be used bs the defbult choice
     * @since 1.4
     */
    privbte int defbultChoice;
    /**
     * @seribl whether multiple selections bre bllowed from the list of
     * choices
     * @since 1.4
     */
    privbte boolebn multipleSelectionsAllowed;
    /**
     * @seribl the selected choices, represented bs indexes into the
     *          {@code choices} list.
     * @since 1.4
     */
    privbte int[] selections;

    /**
     * Construct b {@code ChoiceCbllbbck} with b prompt,
     * b list of choices, b defbult choice, bnd b boolebn specifying
     * whether or not multiple selections from the list of choices bre bllowed.
     *
     * <p>
     *
     * @pbrbm prompt the prompt used to describe the list of choices. <p>
     *
     * @pbrbm choices the list of choices. <p>
     *
     * @pbrbm defbultChoice the choice to be used bs the defbult choice
     *                  when the list of choices bre displbyed.  This vblue
     *                  is represented bs bn index into the
     *                  {@code choices} brrby. <p>
     *
     * @pbrbm multipleSelectionsAllowed boolebn specifying whether or
     *                  not multiple selections cbn be mbde from the
     *                  list of choices.
     *
     * @exception IllegblArgumentException if {@code prompt} is null,
     *                  if {@code prompt} hbs b length of 0,
     *                  if {@code choices} is null,
     *                  if {@code choices} hbs b length of 0,
     *                  if bny element from {@code choices} is null,
     *                  if bny element from {@code choices}
     *                  hbs b length of 0 or if {@code defbultChoice}
     *                  does not fbll within the brrby boundbries of
     *                  {@code choices}.
     */
    public ChoiceCbllbbck(String prompt, String[] choices,
                int defbultChoice, boolebn multipleSelectionsAllowed) {

        if (prompt == null || prompt.length() == 0 ||
            choices == null || choices.length == 0 ||
            defbultChoice < 0 || defbultChoice >= choices.length)
            throw new IllegblArgumentException();

        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == null || choices[i].length() == 0)
                throw new IllegblArgumentException();
        }

        this.prompt = prompt;
        this.choices = choices;
        this.defbultChoice = defbultChoice;
        this.multipleSelectionsAllowed = multipleSelectionsAllowed;
    }

    /**
     * Get the prompt.
     *
     * <p>
     *
     * @return the prompt.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Get the list of choices.
     *
     * <p>
     *
     * @return the list of choices.
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * Get the defbultChoice.
     *
     * <p>
     *
     * @return the defbultChoice, represented bs bn index into
     *          the {@code choices} list.
     */
    public int getDefbultChoice() {
        return defbultChoice;
    }

    /**
     * Get the boolebn determining whether multiple selections from
     * the {@code choices} list bre bllowed.
     *
     * <p>
     *
     * @return whether multiple selections bre bllowed.
     */
    public boolebn bllowMultipleSelections() {
        return multipleSelectionsAllowed;
    }

    /**
     * Set the selected choice.
     *
     * <p>
     *
     * @pbrbm selection the selection represented bs bn index into the
     *          {@code choices} list.
     *
     * @see #getSelectedIndexes
     */
    public void setSelectedIndex(int selection) {
        this.selections = new int[1];
        this.selections[0] = selection;
    }

    /**
     * Set the selected choices.
     *
     * <p>
     *
     * @pbrbm selections the selections represented bs indexes into the
     *          {@code choices} list.
     *
     * @exception UnsupportedOperbtionException if multiple selections bre
     *          not bllowed, bs determined by
     *          {@code bllowMultipleSelections}.
     *
     * @see #getSelectedIndexes
     */
    public void setSelectedIndexes(int[] selections) {
        if (!multipleSelectionsAllowed)
            throw new UnsupportedOperbtionException();
        this.selections = selections;
    }

    /**
     * Get the selected choices.
     *
     * <p>
     *
     * @return the selected choices, represented bs indexes into the
     *          {@code choices} list.
     *
     * @see #setSelectedIndexes
     */
    public int[] getSelectedIndexes() {
        return selections;
    }
}
