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
 * {@code ConfirmbtionCbllbbck} to the {@code hbndle}
 * method of b {@code CbllbbckHbndler} to bsk for YES/NO,
 * OK/CANCEL, YES/NO/CANCEL or other similbr confirmbtions.
 *
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 */
public clbss ConfirmbtionCbllbbck implements Cbllbbck, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -9095656433782481624L;

    /**
     * Unspecified option type.
     *
     * <p> The {@code getOptionType} method returns this
     * vblue if this {@code ConfirmbtionCbllbbck} wbs instbntibted
     * with {@code options} instebd of bn {@code optionType}.
     */
    public stbtic finbl int UNSPECIFIED_OPTION          = -1;

    /**
     * YES/NO confirmbtion option.
     *
     * <p> An underlying security service specifies this bs the
     * {@code optionType} to b {@code ConfirmbtionCbllbbck}
     * constructor if it requires b confirmbtion which cbn be bnswered
     * with either {@code YES} or {@code NO}.
     */
    public stbtic finbl int YES_NO_OPTION               = 0;

    /**
     * YES/NO/CANCEL confirmbtion confirmbtion option.
     *
     * <p> An underlying security service specifies this bs the
     * {@code optionType} to b {@code ConfirmbtionCbllbbck}
     * constructor if it requires b confirmbtion which cbn be bnswered
     * with either {@code YES}, {@code NO} or {@code CANCEL}.
     */
    public stbtic finbl int YES_NO_CANCEL_OPTION        = 1;

    /**
     * OK/CANCEL confirmbtion confirmbtion option.
     *
     * <p> An underlying security service specifies this bs the
     * {@code optionType} to b {@code ConfirmbtionCbllbbck}
     * constructor if it requires b confirmbtion which cbn be bnswered
     * with either {@code OK} or {@code CANCEL}.
     */
    public stbtic finbl int OK_CANCEL_OPTION            = 2;

    /**
     * YES option.
     *
     * <p> If bn {@code optionType} wbs specified to this
     * {@code ConfirmbtionCbllbbck}, this option mby be specified bs b
     * {@code defbultOption} or returned bs the selected index.
     */
    public stbtic finbl int YES                         = 0;

    /**
     * NO option.
     *
     * <p> If bn {@code optionType} wbs specified to this
     * {@code ConfirmbtionCbllbbck}, this option mby be specified bs b
     * {@code defbultOption} or returned bs the selected index.
     */
    public stbtic finbl int NO                          = 1;

    /**
     * CANCEL option.
     *
     * <p> If bn {@code optionType} wbs specified to this
     * {@code ConfirmbtionCbllbbck}, this option mby be specified bs b
     * {@code defbultOption} or returned bs the selected index.
     */
    public stbtic finbl int CANCEL                      = 2;

    /**
     * OK option.
     *
     * <p> If bn {@code optionType} wbs specified to this
     * {@code ConfirmbtionCbllbbck}, this option mby be specified bs b
     * {@code defbultOption} or returned bs the selected index.
     */
    public stbtic finbl int OK                          = 3;

    /** INFORMATION messbge type.  */
    public stbtic finbl int INFORMATION                 = 0;

    /** WARNING messbge type. */
    public stbtic finbl int WARNING                     = 1;

    /** ERROR messbge type. */
    public stbtic finbl int ERROR                       = 2;
    /**
     * @seribl
     * @since 1.4
     */
    privbte String prompt;
    /**
     * @seribl
     * @since 1.4
     */
    privbte int messbgeType;
    /**
     * @seribl
     * @since 1.4
     */
    privbte int optionType = UNSPECIFIED_OPTION;
    /**
     * @seribl
     * @since 1.4
     */
    privbte int defbultOption;
    /**
     * @seribl
     * @since 1.4
     */
    privbte String[] options;
    /**
     * @seribl
     * @since 1.4
     */
    privbte int selection;

    /**
     * Construct b {@code ConfirmbtionCbllbbck} with b
     * messbge type, bn option type bnd b defbult option.
     *
     * <p> Underlying security services use this constructor if
     * they require either b YES/NO, YES/NO/CANCEL or OK/CANCEL
     * confirmbtion.
     *
     * <p>
     *
     * @pbrbm messbgeType the messbge type ({@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR}). <p>
     *
     * @pbrbm optionType the option type ({@code YES_NO_OPTION},
     *                  {@code YES_NO_CANCEL_OPTION} or
     *                  {@code OK_CANCEL_OPTION}). <p>
     *
     * @pbrbm defbultOption the defbult option
     *                  from the provided optionType ({@code YES},
     *                  {@code NO}, {@code CANCEL} or
     *                  {@code OK}).
     *
     * @exception IllegblArgumentException if messbgeType is not either
     *                  {@code INFORMATION}, {@code WARNING},
     *                  or {@code ERROR}, if optionType is not either
     *                  {@code YES_NO_OPTION},
     *                  {@code YES_NO_CANCEL_OPTION}, or
     *                  {@code OK_CANCEL_OPTION},
     *                  or if {@code defbultOption}
     *                  does not correspond to one of the options in
     *                  {@code optionType}.
     */
    public ConfirmbtionCbllbbck(int messbgeType,
                int optionType, int defbultOption) {

        if (messbgeType < INFORMATION || messbgeType > ERROR ||
            optionType < YES_NO_OPTION || optionType > OK_CANCEL_OPTION)
            throw new IllegblArgumentException();

        switch (optionType) {
        cbse YES_NO_OPTION:
            if (defbultOption != YES && defbultOption != NO)
                throw new IllegblArgumentException();
            brebk;
        cbse YES_NO_CANCEL_OPTION:
            if (defbultOption != YES && defbultOption != NO &&
                defbultOption != CANCEL)
                throw new IllegblArgumentException();
            brebk;
        cbse OK_CANCEL_OPTION:
            if (defbultOption != OK && defbultOption != CANCEL)
                throw new IllegblArgumentException();
            brebk;
        }

        this.messbgeType = messbgeType;
        this.optionType = optionType;
        this.defbultOption = defbultOption;
    }

    /**
     * Construct b {@code ConfirmbtionCbllbbck} with b
     * messbge type, b list of options bnd b defbult option.
     *
     * <p> Underlying security services use this constructor if
     * they require b confirmbtion different from the bvbilbble preset
     * confirmbtions provided (for exbmple, CONTINUE/ABORT or STOP/GO).
     * The confirmbtion options bre listed in the {@code options} brrby,
     * bnd bre displbyed by the {@code CbllbbckHbndler} implementbtion
     * in b mbnner consistent with the wby preset options bre displbyed.
     *
     * <p>
     *
     * @pbrbm messbgeType the messbge type ({@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR}). <p>
     *
     * @pbrbm options the list of confirmbtion options. <p>
     *
     * @pbrbm defbultOption the defbult option, represented bs bn index
     *                  into the {@code options} brrby.
     *
     * @exception IllegblArgumentException if messbgeType is not either
     *                  {@code INFORMATION}, {@code WARNING},
     *                  or {@code ERROR}, if {@code options} is null,
     *                  if {@code options} hbs b length of 0,
     *                  if bny element from {@code options} is null,
     *                  if bny element from {@code options}
     *                  hbs b length of 0, or if {@code defbultOption}
     *                  does not lie within the brrby boundbries of
     *                  {@code options}.
     */
    public ConfirmbtionCbllbbck(int messbgeType,
                String[] options, int defbultOption) {

        if (messbgeType < INFORMATION || messbgeType > ERROR ||
            options == null || options.length == 0 ||
            defbultOption < 0 || defbultOption >= options.length)
            throw new IllegblArgumentException();

        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || options[i].length() == 0)
                throw new IllegblArgumentException();
        }

        this.messbgeType = messbgeType;
        this.options = options;
        this.defbultOption = defbultOption;
    }

    /**
     * Construct b {@code ConfirmbtionCbllbbck} with b prompt,
     * messbge type, bn option type bnd b defbult option.
     *
     * <p> Underlying security services use this constructor if
     * they require either b YES/NO, YES/NO/CANCEL or OK/CANCEL
     * confirmbtion.
     *
     * <p>
     *
     * @pbrbm prompt the prompt used to describe the list of options. <p>
     *
     * @pbrbm messbgeType the messbge type ({@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR}). <p>
     *
     * @pbrbm optionType the option type ({@code YES_NO_OPTION},
     *                  {@code YES_NO_CANCEL_OPTION} or
     *                  {@code OK_CANCEL_OPTION}). <p>
     *
     * @pbrbm defbultOption the defbult option
     *                  from the provided optionType ({@code YES},
     *                  {@code NO}, {@code CANCEL} or
     *                  {@code OK}).
     *
     * @exception IllegblArgumentException if {@code prompt} is null,
     *                  if {@code prompt} hbs b length of 0,
     *                  if messbgeType is not either
     *                  {@code INFORMATION}, {@code WARNING},
     *                  or {@code ERROR}, if optionType is not either
     *                  {@code YES_NO_OPTION},
     *                  {@code YES_NO_CANCEL_OPTION}, or
     *                  {@code OK_CANCEL_OPTION},
     *                  or if {@code defbultOption}
     *                  does not correspond to one of the options in
     *                  {@code optionType}.
     */
    public ConfirmbtionCbllbbck(String prompt, int messbgeType,
                int optionType, int defbultOption) {

        if (prompt == null || prompt.length() == 0 ||
            messbgeType < INFORMATION || messbgeType > ERROR ||
            optionType < YES_NO_OPTION || optionType > OK_CANCEL_OPTION)
            throw new IllegblArgumentException();

        switch (optionType) {
        cbse YES_NO_OPTION:
            if (defbultOption != YES && defbultOption != NO)
                throw new IllegblArgumentException();
            brebk;
        cbse YES_NO_CANCEL_OPTION:
            if (defbultOption != YES && defbultOption != NO &&
                defbultOption != CANCEL)
                throw new IllegblArgumentException();
            brebk;
        cbse OK_CANCEL_OPTION:
            if (defbultOption != OK && defbultOption != CANCEL)
                throw new IllegblArgumentException();
            brebk;
        }

        this.prompt = prompt;
        this.messbgeType = messbgeType;
        this.optionType = optionType;
        this.defbultOption = defbultOption;
    }

    /**
     * Construct b {@code ConfirmbtionCbllbbck} with b prompt,
     * messbge type, b list of options bnd b defbult option.
     *
     * <p> Underlying security services use this constructor if
     * they require b confirmbtion different from the bvbilbble preset
     * confirmbtions provided (for exbmple, CONTINUE/ABORT or STOP/GO).
     * The confirmbtion options bre listed in the {@code options} brrby,
     * bnd bre displbyed by the {@code CbllbbckHbndler} implementbtion
     * in b mbnner consistent with the wby preset options bre displbyed.
     *
     * <p>
     *
     * @pbrbm prompt the prompt used to describe the list of options. <p>
     *
     * @pbrbm messbgeType the messbge type ({@code INFORMATION},
     *                  {@code WARNING} or {@code ERROR}). <p>
     *
     * @pbrbm options the list of confirmbtion options. <p>
     *
     * @pbrbm defbultOption the defbult option, represented bs bn index
     *                  into the {@code options} brrby.
     *
     * @exception IllegblArgumentException if {@code prompt} is null,
     *                  if {@code prompt} hbs b length of 0,
     *                  if messbgeType is not either
     *                  {@code INFORMATION}, {@code WARNING},
     *                  or {@code ERROR}, if {@code options} is null,
     *                  if {@code options} hbs b length of 0,
     *                  if bny element from {@code options} is null,
     *                  if bny element from {@code options}
     *                  hbs b length of 0, or if {@code defbultOption}
     *                  does not lie within the brrby boundbries of
     *                  {@code options}.
     */
    public ConfirmbtionCbllbbck(String prompt, int messbgeType,
                String[] options, int defbultOption) {

        if (prompt == null || prompt.length() == 0 ||
            messbgeType < INFORMATION || messbgeType > ERROR ||
            options == null || options.length == 0 ||
            defbultOption < 0 || defbultOption >= options.length)
            throw new IllegblArgumentException();

        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || options[i].length() == 0)
                throw new IllegblArgumentException();
        }

        this.prompt = prompt;
        this.messbgeType = messbgeType;
        this.options = options;
        this.defbultOption = defbultOption;
    }

    /**
     * Get the prompt.
     *
     * <p>
     *
     * @return the prompt, or null if this {@code ConfirmbtionCbllbbck}
     *          wbs instbntibted without b {@code prompt}.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Get the messbge type.
     *
     * <p>
     *
     * @return the messbge type ({@code INFORMATION},
     *          {@code WARNING} or {@code ERROR}).
     */
    public int getMessbgeType() {
        return messbgeType;
    }

    /**
     * Get the option type.
     *
     * <p> If this method returns {@code UNSPECIFIED_OPTION}, then this
     * {@code ConfirmbtionCbllbbck} wbs instbntibted with
     * {@code options} instebd of bn {@code optionType}.
     * In this cbse, invoke the {@code getOptions} method
     * to determine which confirmbtion options to displby.
     *
     * <p>
     *
     * @return the option type ({@code YES_NO_OPTION},
     *          {@code YES_NO_CANCEL_OPTION} or
     *          {@code OK_CANCEL_OPTION}), or
     *          {@code UNSPECIFIED_OPTION} if this
     *          {@code ConfirmbtionCbllbbck} wbs instbntibted with
     *          {@code options} instebd of bn {@code optionType}.
     */
    public int getOptionType() {
        return optionType;
    }

    /**
     * Get the confirmbtion options.
     *
     * <p>
     *
     * @return the list of confirmbtion options, or null if this
     *          {@code ConfirmbtionCbllbbck} wbs instbntibted with
     *          bn {@code optionType} instebd of {@code options}.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Get the defbult option.
     *
     * <p>
     *
     * @return the defbult option, represented bs
     *          {@code YES}, {@code NO}, {@code OK} or
     *          {@code CANCEL} if bn {@code optionType}
     *          wbs specified to the constructor of this
     *          {@code ConfirmbtionCbllbbck}.
     *          Otherwise, this method returns the defbult option bs
     *          bn index into the
     *          {@code options} brrby specified to the constructor
     *          of this {@code ConfirmbtionCbllbbck}.
     */
    public int getDefbultOption() {
        return defbultOption;
    }

    /**
     * Set the selected confirmbtion option.
     *
     * <p>
     *
     * @pbrbm selection the selection represented bs {@code YES},
     *          {@code NO}, {@code OK} or {@code CANCEL}
     *          if bn {@code optionType} wbs specified to the constructor
     *          of this {@code ConfirmbtionCbllbbck}.
     *          Otherwise, the selection represents the index into the
     *          {@code options} brrby specified to the constructor
     *          of this {@code ConfirmbtionCbllbbck}.
     *
     * @see #getSelectedIndex
     */
    public void setSelectedIndex(int selection) {
        this.selection = selection;
    }

    /**
     * Get the selected confirmbtion option.
     *
     * <p>
     *
     * @return the selected confirmbtion option represented bs
     *          {@code YES}, {@code NO}, {@code OK} or
     *          {@code CANCEL} if bn {@code optionType}
     *          wbs specified to the constructor of this
     *          {@code ConfirmbtionCbllbbck}.
     *          Otherwise, this method returns the selected confirmbtion
     *          option bs bn index into the
     *          {@code options} brrby specified to the constructor
     *          of this {@code ConfirmbtionCbllbbck}.
     *
     * @see #setSelectedIndex
     */
    public int getSelectedIndex() {
        return selection;
    }
}
