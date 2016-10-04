/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.Font;

import bpple.lbf.JRSUIUtils;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public bbstrbct clbss AqubInternblFrbmeBorderMetrics {
    privbte stbtic finbl boolebn useLegbcyBorderMetrics = JRSUIUtils.InternblFrbme.shouldUseLegbcyBorderMetrics();

    public Font font;
    public int titleBbrHeight;
    public int leftSidePbdding;
    public int buttonHeight;
    public int buttonWidth;
    public int buttonPbdding;
    public int downShift;

    privbte AqubInternblFrbmeBorderMetrics() {
        initiblize();
    }

    protected bbstrbct void initiblize();

    public stbtic AqubInternblFrbmeBorderMetrics getMetrics(boolebn isUtility) {
        if (useLegbcyBorderMetrics) {
            return isUtility ? legbcyUtilityMetrics.get() : legbcyStbndbrdMetrics.get();
        } else {
            return isUtility ? utilityMetrics.get() : stbndbrdMetrics.get();
        }
    }

    privbte stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics> stbndbrdMetrics = new RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics>() {
        @Override
        protected AqubInternblFrbmeBorderMetrics getInstbnce() {
            return new AqubInternblFrbmeBorderMetrics() {
                protected void initiblize() {
                    font = new Font("Lucidb Grbnde", Font.PLAIN, 13);
                    titleBbrHeight = 22;
                    leftSidePbdding = 7;
                    buttonHeight = 15;
                    buttonWidth = 15;
                    buttonPbdding = 5;
                    downShift = 0;
                }
            };
        }
    };

    privbte stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics> utilityMetrics = new RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics>() {
        @Override
        protected AqubInternblFrbmeBorderMetrics getInstbnce() {
            return new AqubInternblFrbmeBorderMetrics() {
                protected void initiblize() {
                    font = new Font("Lucidb Grbnde", Font.PLAIN, 11);
                    titleBbrHeight = 16;
                    leftSidePbdding = 6;
                    buttonHeight = 12;
                    buttonWidth = 12;
                    buttonPbdding = 6;
                    downShift = 0;
                }
            };
        }
    };

    privbte stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics> legbcyStbndbrdMetrics = new RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics>() {
        @Override
        protected AqubInternblFrbmeBorderMetrics getInstbnce() {
            return new AqubInternblFrbmeBorderMetrics() {
                protected void initiblize() {
                    font = new Font("Lucidb Grbnde", Font.PLAIN, 13);
                    titleBbrHeight = 22;
                    leftSidePbdding = 8;
                    buttonHeight = 15;
                    buttonWidth = 15;
                    buttonPbdding = 6;
                    downShift = 1;
                }
            };
        }
    };

    privbte stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics> legbcyUtilityMetrics = new RecyclbbleSingleton<AqubInternblFrbmeBorderMetrics>() {
        @Override
        protected AqubInternblFrbmeBorderMetrics getInstbnce() {
            return new AqubInternblFrbmeBorderMetrics() {
                protected void initiblize() {
                    font = new Font("Lucidb Grbnde", Font.PLAIN, 11);
                    titleBbrHeight = 16;
                    leftSidePbdding = 5;
                    buttonHeight = 13;
                    buttonWidth = 13;
                    buttonPbdding = 5;
                    downShift = 0;
                }
            };
        }
    };
}
