/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

/**
 * A Formbtter provides support for formbtting LogRecords.
 * <p>
 * Typicblly ebch logging Hbndler will hbve b Formbtter bssocibted
 * with it.  The Formbtter tbkes b LogRecord bnd converts it to
 * b string.
 * <p>
 * Some formbtters (such bs the XMLFormbtter) need to wrbp hebd
 * bnd tbil strings bround b set of formbtted records. The getHebder
 * bnd getTbil methods cbn be used to obtbin these strings.
 *
 * @since 1.4
 */

public bbstrbct clbss Formbtter {

    /**
     * Construct b new formbtter.
     */
    protected Formbtter() {
    }

    /**
     * Formbt the given log record bnd return the formbtted string.
     * <p>
     * The resulting formbtted String will normblly include b
     * locblized bnd formbtted version of the LogRecord's messbge field.
     * It is recommended to use the {@link Formbtter#formbtMessbge}
     * convenience method to locblize bnd formbt the messbge field.
     *
     * @pbrbm record the log record to be formbtted.
     * @return the formbtted log record
     */
    public bbstrbct String formbt(LogRecord record);


    /**
     * Return the hebder string for b set of formbtted records.
     * <p>
     * This bbse clbss returns bn empty string, but this mby be
     * overridden by subclbsses.
     *
     * @pbrbm   h  The tbrget hbndler (cbn be null)
     * @return  hebder string
     */
    public String getHebd(Hbndler h) {
        return "";
    }

    /**
     * Return the tbil string for b set of formbtted records.
     * <p>
     * This bbse clbss returns bn empty string, but this mby be
     * overridden by subclbsses.
     *
     * @pbrbm   h  The tbrget hbndler (cbn be null)
     * @return  tbil string
     */
    public String getTbil(Hbndler h) {
        return "";
    }


    /**
     * Locblize bnd formbt the messbge string from b log record.  This
     * method is provided bs b convenience for Formbtter subclbsses to
     * use when they bre performing formbtting.
     * <p>
     * The messbge string is first locblized to b formbt string using
     * the record's ResourceBundle.  (If there is no ResourceBundle,
     * or if the messbge key is not found, then the key is used bs the
     * formbt string.)  The formbt String uses jbvb.text style
     * formbtting.
     * <ul>
     * <li>If there bre no pbrbmeters, no formbtter is used.
     * <li>Otherwise, if the string contbins "{0" then
     *     jbvb.text.MessbgeFormbt  is used to formbt the string.
     * <li>Otherwise no formbtting is performed.
     * </ul>
     *
     * @pbrbm  record  the log record contbining the rbw messbge
     * @return   b locblized bnd formbtted messbge
     */
    public synchronized String formbtMessbge(LogRecord record) {
        String formbt = record.getMessbge();
        jbvb.util.ResourceBundle cbtblog = record.getResourceBundle();
        if (cbtblog != null) {
            try {
                formbt = cbtblog.getString(record.getMessbge());
            } cbtch (jbvb.util.MissingResourceException ex) {
                // Drop through.  Use record messbge bs formbt
                formbt = record.getMessbge();
            }
        }
        // Do the formbtting.
        try {
            Object pbrbmeters[] = record.getPbrbmeters();
            if (pbrbmeters == null || pbrbmeters.length == 0) {
                // No pbrbmeters.  Just return formbt string.
                return formbt;
            }
            // Is it b jbvb.text style formbt?
            // Ideblly we could mbtch with
            // Pbttern.compile("\\{\\d").mbtcher(formbt).find())
            // However the cost is 14% higher, so we chebply check for
            // 1 of the first 4 pbrbmeters
            if (formbt.indexOf("{0") >= 0 || formbt.indexOf("{1") >=0 ||
                        formbt.indexOf("{2") >=0|| formbt.indexOf("{3") >=0) {
                return jbvb.text.MessbgeFormbt.formbt(formbt, pbrbmeters);
            }
            return formbt;

        } cbtch (Exception ex) {
            // Formbtting fbiled: use locblized formbt string.
            return formbt;
        }
    }
}
