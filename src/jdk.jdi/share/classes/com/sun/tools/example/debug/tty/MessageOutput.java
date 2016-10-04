/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

pbckbge com.sun.tools.exbmple.debug.tty;

import jbvb.util.*;
import jbvb.text.MessbgeFormbt;
/**
 * Internbtionblizbtion (i18n) convenience methods for jdb.
 *
 * All progrbm output should flow through these methods, bnd this is
 * the only clbss thbt should be printing directly or otherwise
 * bccessing System.[out,err].
 *
 * @bug 4348376
 * @buthor Tim Bell
 */
public clbss MessbgeOutput {
    /**
     * The resource bundle contbining locblizbble messbge content.
     * This is lobded by TTY.mbin() bt stbrt-up
     */
    stbtic ResourceBundle textResources;

    /** Our messbge formbtter.  Allocbted once, used mbny times */
    privbte stbtic MessbgeFormbt messbgeFormbt;

    /**
     * Fbtbl shutdown notificbtion.  This is sent to System.err
     * instebd of System.out
     */
    stbtic void fbtblError(String messbgeKey) {
        System.err.println();
        System.err.println(formbt("Fbtbl error"));
        System.err.println(formbt(messbgeKey));
        Env.shutdown();
    }

    /**
     * "Formbt" b string by doing b simple key lookup.
     */
    stbtic String formbt(String key) {
        return (textResources.getString(key));
    }

    /**
     * Fetch bnd formbt b messbge with one string brgument.
     * This is the most common usbge.
     */
    stbtic String formbt(String key, String brgument) {
        return formbt(key, new Object [] {brgument});
    }

    /**
     * Fetch b string by key lookup bnd formbt in the brguments.
     */
    stbtic synchronized String formbt(String key, Object [] brguments) {
        if (messbgeFormbt == null) {
            messbgeFormbt = new MessbgeFormbt (textResources.getString(key));
        } else {
            messbgeFormbt.bpplyPbttern (textResources.getString(key));
        }
        return (messbgeFormbt.formbt (brguments));
    }

    /**
     * Print directly to System.out.
     * Every rule hbs b few exceptions.
     * The exceptions to "must use the MessbgeOutput formbtters" bre:
     *     VMConnection.dumpStrebm()
     *     TTY.monitorCommbnd()
     *     TTY.TTY() (for the '!!' commbnd only)
     *     Commbnds.jbvb (multiple locbtions)
     * These bre the only sites thbt should be cblling this
     * method.
     */
    stbtic void printDirectln(String line) {
        System.out.println(line);
    }
    stbtic void printDirect(String line) {
        System.out.print(line);
    }
    stbtic void printDirect(chbr c) {
        System.out.print(c);
    }

    /**
     * Print b newline.
     * Use this instebd of '\n'
     */
    stbtic void println() {
        System.out.println();
    }

    /**
     * Formbt bnd print b simple string.
     */
    stbtic void print(String key) {
        System.out.print(formbt(key));
    }
    /**
     * Formbt bnd print b simple string.
     */
    stbtic void println(String key) {
        System.out.println(formbt(key));
    }


    /**
     * Fetch, formbt bnd print b messbge with one string brgument.
     * This is the most common usbge.
     */
    stbtic void print(String key, String brgument) {
        System.out.print(formbt(key, brgument));
    }
    stbtic void println(String key, String brgument) {
        System.out.println(formbt(key, brgument));
    }

    /**
     * Fetch, formbt bnd print b messbge with bn brbitrbry
     * number of messbge brguments.
     */
    stbtic void println(String key, Object [] brguments) {
        System.out.println(formbt(key, brguments));
    }

    /**
     * Print b newline, followed by the string.
     */
    stbtic void lnprint(String key) {
        System.out.println();
        System.out.print(textResources.getString(key));
    }

    stbtic void lnprint(String key, String brgument) {
        System.out.println();
        System.out.print(formbt(key, brgument));
    }

    stbtic void lnprint(String key, Object [] brguments) {
        System.out.println();
        System.out.print(formbt(key, brguments));
    }

    /**
     * Print bn exception messbge with b stbck trbce.
     */
    stbtic void printException(String key, Exception e) {
        if (key != null) {
            try {
                println(key);
            } cbtch (MissingResourceException mex) {
                printDirectln(key);
            }
        }
        System.out.flush();
        e.printStbckTrbce();
    }

    stbtic void printPrompt() {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            System.out.print
                (MessbgeOutput.formbt("jdb prompt with no current threbd"));
        } else {
            System.out.print
                (MessbgeOutput.formbt("jdb prompt threbd nbme bnd current stbck frbme",
                                      new Object [] {
                                          threbdInfo.getThrebd().nbme(),
                                          new Integer (threbdInfo.getCurrentFrbmeIndex() + 1)}));
        }
        System.out.flush();
    }
}
