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
 * {@code TextInputCbllbbck} to the {@code hbndle}
 * method of b {@code CbllbbckHbndler} to retrieve generic text
 * informbtion.
 *
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 */
public clbss TextInputCbllbbck implements Cbllbbck, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -8064222478852811804L;

    /**
     * @seribl
     * @since 1.4
     */
    privbte String prompt;
    /**
     * @seribl
     * @since 1.4
     */
    privbte String defbultText;
    /**
     * @seribl
     * @since 1.4
     */
    privbte String inputText;

    /**
     * Construct b {@code TextInputCbllbbck} with b prompt.
     *
     * <p>
     *
     * @pbrbm prompt the prompt used to request the informbtion.
     *
     * @exception IllegblArgumentException if {@code prompt} is null
     *                  or if {@code prompt} hbs b length of 0.
     */
    public TextInputCbllbbck(String prompt) {
        if (prompt == null || prompt.length() == 0)
            throw new IllegblArgumentException();
        this.prompt = prompt;
    }

    /**
     * Construct b {@code TextInputCbllbbck} with b prompt
     * bnd defbult input vblue.
     *
     * <p>
     *
     * @pbrbm prompt the prompt used to request the informbtion. <p>
     *
     * @pbrbm defbultText the text to be used bs the defbult text displbyed
     *                  with the prompt.
     *
     * @exception IllegblArgumentException if {@code prompt} is null,
     *                  if {@code prompt} hbs b length of 0,
     *                  if {@code defbultText} is null
     *                  or if {@code defbultText} hbs b length of 0.
     */
    public TextInputCbllbbck(String prompt, String defbultText) {
        if (prompt == null || prompt.length() == 0 ||
            defbultText == null || defbultText.length() == 0)
            throw new IllegblArgumentException();

        this.prompt = prompt;
        this.defbultText = defbultText;
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
     * Get the defbult text.
     *
     * <p>
     *
     * @return the defbult text, or null if this {@code TextInputCbllbbck}
     *          wbs not instbntibted with {@code defbultText}.
     */
    public String getDefbultText() {
        return defbultText;
    }

    /**
     * Set the retrieved text.
     *
     * <p>
     *
     * @pbrbm text the retrieved text, which mby be null.
     *
     * @see #getText
     */
    public void setText(String text) {
        this.inputText = text;
    }

    /**
     * Get the retrieved text.
     *
     * <p>
     *
     * @return the retrieved text, which mby be null.
     *
     * @see #setText
     */
    public String getText() {
        return inputText;
    }
}
