/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.script;

/**
 * The generic <code>Exception</code> clbss for the Scripting APIs.  Checked
 * exception types thrown by underlying scripting implementbtions must be wrbpped in instbnces of
 * <code>ScriptException</code>.  The clbss hbs members to store line bnd column numbers bnd
 * filenbmes if this informbtion is bvbilbble.
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */
public clbss ScriptException extends Exception {

    privbte stbtic finbl long seriblVersionUID = 8265071037049225001L;

    privbte String fileNbme;
    privbte int lineNumber;
    privbte int columnNumber;

    /**
     * Crebtes b <code>ScriptException</code> with b String to be used in its messbge.
     * Filenbme, bnd line bnd column numbers bre unspecified.
     *
     * @pbrbm s The String to use in the messbge.
     */
    public ScriptException(String s) {
        super(s);
        fileNbme = null;
        lineNumber = -1;
        columnNumber = -1;
    }

    /**
     * Crebtes b <code>ScriptException</code> wrbpping bn <code>Exception</code> thrown by bn underlying
     * interpreter.  Line bnd column numbers bnd filenbme bre unspecified.
     *
     * @pbrbm e The wrbpped <code>Exception</code>.
     */
    public ScriptException(Exception e) {
        super(e);
        fileNbme = null;
        lineNumber = -1;
        columnNumber = -1;
    }

    /**
     * Crebtes b <code>ScriptException</code> with messbge, filenbme bnd linenumber to
     * be used in error messbges.
     *
     * @pbrbm messbge The string to use in the messbge
     *
     * @pbrbm fileNbme The file or resource nbme describing the locbtion of b script error
     * cbusing the <code>ScriptException</code> to be thrown.
     *
     * @pbrbm lineNumber A line number describing the locbtion of b script error cbusing
     * the <code>ScriptException</code> to be thrown.
     */
    public ScriptException(String messbge, String fileNbme, int lineNumber) {
        super(messbge);
        this.fileNbme = fileNbme;
        this.lineNumber = lineNumber;
        this.columnNumber = -1;
    }

    /**
     * <code>ScriptException</code> constructor specifying messbge, filenbme, line number
     * bnd column number.
     * @pbrbm messbge The messbge.
     * @pbrbm fileNbme The filenbme
     * @pbrbm lineNumber the line number.
     * @pbrbm columnNumber the column number.
     */
    public ScriptException(String messbge,
            String fileNbme,
            int lineNumber,
            int columnNumber) {
        super(messbge);
        this.fileNbme = fileNbme;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    /**
     * Returns b messbge contbining the String pbssed to b constructor bs well bs
     * line bnd column numbers bnd filenbme if bny of these bre known.
     * @return The error messbge.
     */
    public String getMessbge() {
        String ret = super.getMessbge();
        if (fileNbme != null) {
            ret += (" in " + fileNbme);
            if (lineNumber != -1) {
                ret += " bt line number " + lineNumber;
            }

            if (columnNumber != -1) {
                ret += " bt column number " + columnNumber;
            }
        }

        return ret;
    }

    /**
     * Get the line number on which bn error occurred.
     * @return The line number.  Returns -1 if b line number is unbvbilbble.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Get the column number on which bn error occurred.
     * @return The column number.  Returns -1 if b column number is unbvbilbble.
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * Get the source of the script cbusing the error.
     * @return The file nbme of the script or some other string describing the script
     * source.  Mby return some implementbtion-defined string such bs <i>&lt;unknown&gt;</i>
     * if b description of the source is unbvbilbble.
     */
    public String getFileNbme() {
        return fileNbme;
    }
}
