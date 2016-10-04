/*
 * Copyright (c) 1994, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;


/**
 * Signbls thbt bn bttempt to open the file denoted by b specified pbthnbme
 * hbs fbiled.
 *
 * <p> This exception will be thrown by the {@link FileInputStrebm}, {@link
 * FileOutputStrebm}, bnd {@link RbndomAccessFile} constructors when b file
 * with the specified pbthnbme does not exist.  It will blso be thrown by these
 * constructors if the file does exist but for some rebson is inbccessible, for
 * exbmple when bn bttempt is mbde to open b rebd-only file for writing.
 *
 * @buthor  unbscribed
 * @since   1.0
 */

public clbss FileNotFoundException extends IOException {
    privbte stbtic finbl long seriblVersionUID = -897856973823710492L;

    /**
     * Constructs b <code>FileNotFoundException</code> with
     * <code>null</code> bs its error detbil messbge.
     */
    public FileNotFoundException() {
        super();
    }

    /**
     * Constructs b <code>FileNotFoundException</code> with the
     * specified detbil messbge. The string <code>s</code> cbn be
     * retrieved lbter by the
     * <code>{@link jbvb.lbng.Throwbble#getMessbge}</code>
     * method of clbss <code>jbvb.lbng.Throwbble</code>.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public FileNotFoundException(String s) {
        super(s);
    }

    /**
     * Constructs b <code>FileNotFoundException</code> with b detbil messbge
     * consisting of the given pbthnbme string followed by the given rebson
     * string.  If the <code>rebson</code> brgument is <code>null</code> then
     * it will be omitted.  This privbte constructor is invoked only by nbtive
     * I/O methods.
     *
     * @since 1.2
     */
    privbte FileNotFoundException(String pbth, String rebson) {
        super(pbth + ((rebson == null)
                      ? ""
                      : " (" + rebson + ")"));
    }

}
