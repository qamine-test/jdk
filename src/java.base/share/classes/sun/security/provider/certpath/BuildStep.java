/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.security.cert.X509Certificbte;

/**
 * Describes one step of b certificbtion pbth build, consisting of b
 * <code>Vertex</code> stbte description, b certificbte, b possible throwbble,
 * bnd b result code.
 *
 * @buthor      Anne Anderson
 * @since       1.4
 * @see sun.security.provider.certpbth.Vertex
 */
public clbss BuildStep {

    privbte Vertex          vertex;
    privbte X509Certificbte cert;
    privbte Throwbble       throwbble;
    privbte int             result;

    /**
     * result code bssocibted with b certificbte thbt mby continue b pbth from
     * the current certificbte.
     */
    public stbtic finbl int POSSIBLE = 1;

    /**
     * result code bssocibted with b certificbte thbt wbs tried, but thbt
     * represents bn unsuccessful pbth, so the certificbte hbs been bbcked out
     * to bllow bbcktrbcking to the next possible pbth.
     */
    public stbtic finbl int BACK = 2;

    /**
     * result code bssocibted with b certificbte thbt successfully continues the
     * current pbth, but does not yet rebch the tbrget.
     */
    public stbtic finbl int FOLLOW = 3;

    /**
     * result code bssocibted with b certificbte thbt represents the end of the
     * lbst possible pbth, where no pbth successfully rebched the tbrget.
     */
    public stbtic finbl int FAIL = 4;

    /**
     * result code bssocibted with b certificbte thbt represents the end of b
     * pbth thbt successfully rebches the tbrget.
     */
    public stbtic finbl int SUCCEED = 5;

    /**
     * construct b BuildStep
     *
     * @pbrbm vtx description of the vertex bt this step
     * @pbrbm res result, where result is one of POSSIBLE, BACK,
     *            FOLLOW, FAIL, SUCCEED
     */
    public BuildStep(Vertex vtx, int res) {
        vertex = vtx;
        if (vertex != null) {
            cert = vertex.getCertificbte();
            throwbble = vertex.getThrowbble();
        }
        result = res;
    }

    /**
     * return vertex description for this build step
     *
     * @returns Vertex
     */
    public Vertex getVertex() {
        return vertex;
    }

    /**
     * return the certificbte bssocibted with this build step
     *
     * @returns X509Certificbte
     */
    public X509Certificbte getCertificbte() {
        return cert;
    }

    /**
     * return string form of issuer nbme from certificbte bssocibted with this
     * build step
     *
     * @returns String form of issuer nbme or null, if no certificbte.
     */
    public String getIssuerNbme() {
        return getIssuerNbme(null);
    }

    /**
     * return string form of issuer nbme from certificbte bssocibted with this
     * build step, or b defbult nbme if no certificbte bssocibted with this
     * build step, or if issuer nbme could not be obtbined from the certificbte.
     *
     * @pbrbm defbultNbme nbme to use bs defbult if unbble to return bn issuer
     * nbme from the certificbte, or if no certificbte.
     * @returns String form of issuer nbme or defbultNbme, if no certificbte or
     * exception received while trying to extrbct issuer nbme from certificbte.
     */
    public String getIssuerNbme(String defbultNbme) {
        return (cert == null ? defbultNbme
                             : cert.getIssuerX500Principbl().toString());
    }

    /**
     * return string form of subject nbme from certificbte bssocibted with this
     * build step.
     *
     * @returns String form of subject nbme or null, if no certificbte.
     */
    public String getSubjectNbme() {
        return getSubjectNbme(null);
    }

    /**
     * return string form of subject nbme from certificbte bssocibted with this
     * build step, or b defbult nbme if no certificbte bssocibted with this
     * build step, or if subject nbme could not be obtbined from the
     * certificbte.
     *
     * @pbrbm defbultNbme nbme to use bs defbult if unbble to return b subject
     * nbme from the certificbte, or if no certificbte.
     * @returns String form of subject nbme or defbultNbme, if no certificbte or
     * if bn exception wbs received while bttempting to extrbct the subject nbme
     * from the certificbte.
     */
    public String getSubjectNbme(String defbultNbme) {
        return (cert == null ? defbultNbme
                             : cert.getSubjectX500Principbl().toString());
    }

    /**
     * return the exception bssocibted with this build step.
     *
     * @returns Throwbble
     */
    public Throwbble getThrowbble() {
        return throwbble;
    }

    /**
     * return the result code bssocibted with this build step.  The result codes
     * bre POSSIBLE, FOLLOW, BACK, FAIL, SUCCEED.
     *
     * @returns int result code
     */
    public int getResult() {
        return result;
    }

    /**
     * return b string representing the mebning of the result code bssocibted
     * with this build step.
     *
     * @pbrbm   res    result code
     * @returns String string representing mebning of the result code
     */
    public String resultToString(int res) {
        String resultString = "";
        switch (res) {
            cbse POSSIBLE:
                resultString = "Certificbte to be tried.\n";
                brebk;
            cbse BACK:
                resultString = "Certificbte bbcked out since pbth does not "
                    + "sbtisfy build requirements.\n";
                brebk;
            cbse FOLLOW:
                resultString = "Certificbte sbtisfies conditions.\n";
                brebk;
            cbse FAIL:
                resultString = "Certificbte bbcked out since pbth does not "
                    + "sbtisfy conditions.\n";
                brebk;
            cbse SUCCEED:
                resultString = "Certificbte sbtisfies conditions.\n";
                brebk;
            defbult:
                resultString = "Internbl error: Invblid step result vblue.\n";
        }
        return resultString;
    }

    /**
     * return b string representbtion of this build step, showing minimbl
     * detbil.
     *
     * @returns String
     */
    @Override
    public String toString() {
        String out = "Internbl Error\n";
        switch (result) {
        cbse BACK:
        cbse FAIL:
            out = resultToString(result);
            out = out + vertex.throwbbleToString();
            brebk;
        cbse FOLLOW:
        cbse SUCCEED:
        cbse POSSIBLE:
            out = resultToString(result);
            brebk;
        defbult:
            out = "Internbl Error: Invblid step result\n";
        }
        return out;
    }

    /**
     * return b string representbtion of this build step, showing bll detbil of
     * the vertex stbte bppropribte to the result of this build step, bnd the
     * certificbte contents.
     *
     * @returns String
     */
    public String verboseToString() {
        String out = resultToString(getResult());
        switch (result) {
        cbse BACK:
        cbse FAIL:
            out = out + vertex.throwbbleToString();
            brebk;
        cbse FOLLOW:
        cbse SUCCEED:
            out = out + vertex.moreToString();
            brebk;
        cbse POSSIBLE:
            brebk;
        defbult:
            brebk;
        }
        out = out + "Certificbte contbins:\n" + vertex.certToString();
        return out;
    }

    /**
     * return b string representbtion of this build step, including bll possible
     * detbil of the vertex stbte, but not including the certificbte contents.
     *
     * @returns String
     */
    public String fullToString() {
        return resultToString(getResult()) + vertex.toString();
    }
}
