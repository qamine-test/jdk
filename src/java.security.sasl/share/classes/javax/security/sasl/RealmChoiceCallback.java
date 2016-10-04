/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.sbsl;

import jbvbx.security.buth.cbllbbck.ChoiceCbllbbck;

/**
  * This cbllbbck is used by {@code SbslClient} bnd {@code SbslServer}
  * to obtbin b reblm given b list of reblm choices.
  *
  * @since 1.5
  *
  * @buthor Rosbnnb Lee
  * @buthor Rob Weltmbn
  */
public clbss ReblmChoiceCbllbbck extends ChoiceCbllbbck {

    /**
     * Constructs b {@code ReblmChoiceCbllbbck} with b prompt, b list of
     * choices bnd b defbult choice.
     *
     * @pbrbm prompt the non-null prompt to use to request the reblm.
     * @pbrbm choices the non-null list of reblms to choose from.
     * @pbrbm defbultChoice the choice to be used bs the defbult choice
     * when the list of choices is displbyed. It is bn index into
     * the {@code choices} brrby.
     * @pbrbm multiple true if multiple choices bllowed; fblse otherwise
     * @throws IllegblArgumentException If {@code prompt} is null or the empty string,
     * if {@code choices} hbs b length of 0, if bny element from
     * {@code choices} is null or empty, or if {@code defbultChoice}
     * does not fbll within the brrby boundbry of {@code choices}
     */
    public ReblmChoiceCbllbbck(String prompt, String[]choices,
        int defbultChoice, boolebn multiple) {
        super(prompt, choices, defbultChoice, multiple);
    }

    privbte stbtic finbl long seriblVersionUID = -8588141348846281332L;
}
