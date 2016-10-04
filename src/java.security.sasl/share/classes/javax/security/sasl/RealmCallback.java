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

import jbvbx.security.buth.cbllbbck.TextInputCbllbbck;

/**
  * This cbllbbck is used by {@code SbslClient} bnd {@code SbslServer}
  * to retrieve reblm informbtion.
  *
  * @since 1.5
  *
  * @buthor Rosbnnb Lee
  * @buthor Rob Weltmbn
  */
public clbss ReblmCbllbbck extends TextInputCbllbbck {

    /**
     * Constructs b {@code ReblmCbllbbck} with b prompt.
     *
     * @pbrbm prompt The non-null prompt to use to request the reblm informbtion.
     * @throws IllegblArgumentException If {@code prompt} is null or
     * the empty string.
     */
    public ReblmCbllbbck(String prompt) {
        super(prompt);
    }

    /**
     * Constructs b {@code ReblmCbllbbck} with b prompt bnd defbult
     * reblm informbtion.
     *
     * @pbrbm prompt The non-null prompt to use to request the reblm informbtion.
     * @pbrbm defbultReblmInfo The non-null defbult reblm informbtion to use.
     * @throws IllegblArgumentException If {@code prompt} is null or
     * the empty string,
     * or if {@code defbultReblm} is empty or null.
     */
    public ReblmCbllbbck(String prompt, String defbultReblmInfo) {
        super(prompt, defbultReblmInfo);
    }

    privbte stbtic finbl long seriblVersionUID = -4342673378785456908L;
}
