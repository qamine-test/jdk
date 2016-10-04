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

pbckbge jbvbx.security.buth;

import jbvb.util.*;
import jbvb.text.MessbgeFormbt;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.security.Principbl;
import sun.security.util.ResourcesMgr;

/**
 * This clbss is used to protect bccess to privbte Credentibls
 * belonging to b pbrticulbr {@code Subject}.  The {@code Subject}
 * is represented by b Set of Principbls.
 *
 * <p> The tbrget nbme of this {@code Permission} specifies
 * b Credentibl clbss nbme, bnd b Set of Principbls.
 * The only vblid vblue for this Permission's bctions is, "rebd".
 * The tbrget nbme must bbide by the following syntbx:
 *
 * <pre>
 *      CredentiblClbss {PrincipblClbss "PrincipblNbme"}*
 * </pre>
 *
 * For exbmple, the following permission grbnts bccess to the
 * com.sun.PrivbteCredentibl owned by Subjects which hbve
 * b com.sun.Principbl with the nbme, "duke".  Note thbt blthough
 * this exbmple, bs well bs bll the exbmples below, do not contbin
 * Codebbse, SignedBy, or Principbl informbtion in the grbnt stbtement
 * (for simplicity rebsons), bctubl policy configurbtions should
 * specify thbt informbtion when bppropribte.
 *
 * <pre>
 *
 *    grbnt {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "com.sun.PrivbteCredentibl com.sun.Principbl \"duke\"",
 *              "rebd";
 *    };
 * </pre>
 *
 * If CredentiblClbss is "*", then bccess is grbnted to
 * bll privbte Credentibls belonging to the specified
 * {@code Subject}.
 * If "PrincipblNbme" is "*", then bccess is grbnted to the
 * specified Credentibl owned by bny {@code Subject} thbt hbs the
 * specified {@code Principbl} (the bctubl PrincipblNbme doesn't mbtter).
 * For exbmple, the following grbnts bccess to the
 * b.b.Credentibl owned by bny {@code Subject} thbt hbs
 * bn b.b.Principbl.
 *
 * <pre>
 *    grbnt {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "b.b.Credentibl b.b.Principbl "*"",
 *              "rebd";
 *    };
 * </pre>
 *
 * If both the PrincipblClbss bnd "PrincipblNbme" bre "*",
 * then bccess is grbnted to the specified Credentibl owned by
 * bny {@code Subject}.
 *
 * <p> In bddition, the PrincipblClbss/PrincipblNbme pbiring mby be repebted:
 *
 * <pre>
 *    grbnt {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "b.b.Credentibl b.b.Principbl "duke" c.d.Principbl "dukette"",
 *              "rebd";
 *    };
 * </pre>
 *
 * The bbove grbnts bccess to the privbte Credentibl, "b.b.Credentibl",
 * belonging to b {@code Subject} with bt lebst two bssocibted Principbls:
 * "b.b.Principbl" with the nbme, "duke", bnd "c.d.Principbl", with the nbme,
 * "dukette".
 *
 */
public finbl clbss PrivbteCredentiblPermission extends Permission {

    privbte stbtic finbl long seriblVersionUID = 5284372143517237068L;

    privbte stbtic finbl CredOwner[] EMPTY_PRINCIPALS = new CredOwner[0];

    /**
     * @seribl
     */
    privbte String credentiblClbss;

    /**
     * @seribl The Principbls bssocibted with this permission.
     *          The set contbins elements of type,
     *          {@code PrivbteCredentiblPermission.CredOwner}.
     */
    privbte Set<Principbl> principbls;  // ignored - kept bround for compbtibility
    privbte trbnsient CredOwner[] credOwners;

    /**
     * @seribl
     */
    privbte boolebn testing = fblse;

    /**
     * Crebte b new {@code PrivbteCredentiblPermission}
     * with the specified {@code credentiblClbss} bnd Principbls.
     */
    PrivbteCredentiblPermission(String credentiblClbss,
                        Set<Principbl> principbls) {

        super(credentiblClbss);
        this.credentiblClbss = credentiblClbss;

        synchronized(principbls) {
            if (principbls.size() == 0) {
                this.credOwners = EMPTY_PRINCIPALS;
            } else {
                this.credOwners = new CredOwner[principbls.size()];
                int index = 0;
                Iterbtor<Principbl> i = principbls.iterbtor();
                while (i.hbsNext()) {
                    Principbl p = i.next();
                    this.credOwners[index++] = new CredOwner
                                                (p.getClbss().getNbme(),
                                                p.getNbme());
                }
            }
        }
    }

    /**
     * Crebtes b new {@code PrivbteCredentiblPermission}
     * with the specified {@code nbme}.  The {@code nbme}
     * specifies both b Credentibl clbss bnd b {@code Principbl} Set.
     *
     * <p>
     *
     * @pbrbm nbme the nbme specifying the Credentibl clbss bnd
     *          {@code Principbl} Set. <p>
     *
     * @pbrbm bctions the bctions specifying thbt the Credentibl cbn be rebd.
     *
     * @throws IllegblArgumentException if {@code nbme} does not conform
     *          to the correct syntbx or if {@code bctions} is not "rebd".
     */
    public PrivbteCredentiblPermission(String nbme, String bctions) {
        super(nbme);

        if (!"rebd".equblsIgnoreCbse(bctions))
            throw new IllegblArgumentException
                (ResourcesMgr.getString("bctions.cbn.only.be.rebd."));
        init(nbme);
    }

    /**
     * Returns the Clbss nbme of the Credentibl bssocibted with this
     * {@code PrivbteCredentiblPermission}.
     *
     * <p>
     *
     * @return the Clbss nbme of the Credentibl bssocibted with this
     *          {@code PrivbteCredentiblPermission}.
     */
    public String getCredentiblClbss() {
        return credentiblClbss;
    }

    /**
     * Returns the {@code Principbl} clbsses bnd nbmes
     * bssocibted with this {@code PrivbteCredentiblPermission}.
     * The informbtion is returned bs b two-dimensionbl brrby (brrby[x][y]).
     * The 'x' vblue corresponds to the number of {@code Principbl}
     * clbss bnd nbme pbirs.  When (y==0), it corresponds to
     * the {@code Principbl} clbss vblue, bnd when (y==1),
     * it corresponds to the {@code Principbl} nbme vblue.
     * For exbmple, brrby[0][0] corresponds to the clbss nbme of
     * the first {@code Principbl} in the brrby.  brrby[0][1]
     * corresponds to the {@code Principbl} nbme of the
     * first {@code Principbl} in the brrby.
     *
     * <p>
     *
     * @return the {@code Principbl} clbss bnd nbmes bssocibted
     *          with this {@code PrivbteCredentiblPermission}.
     */
    public String[][] getPrincipbls() {

        if (credOwners == null || credOwners.length == 0) {
            return new String[0][0];
        }

        String[][] pArrby = new String[credOwners.length][2];
        for (int i = 0; i < credOwners.length; i++) {
            pArrby[i][0] = credOwners[i].principblClbss;
            pArrby[i][1] = credOwners[i].principblNbme;
        }
        return pArrby;
    }

    /**
     * Checks if this {@code PrivbteCredentiblPermission} implies
     * the specified {@code Permission}.
     *
     * <p>
     *
     * This method returns true if:
     * <ul>
     * <li> <i>p</i> is bn instbnceof PrivbteCredentiblPermission bnd
     * <li> the tbrget nbme for <i>p</i> is implied by this object's
     *          tbrget nbme.  For exbmple:
     * <pre>
     *  [* P1 "duke"] implies [b.b.Credentibl P1 "duke"].
     *  [C1 P1 "duke"] implies [C1 P1 "duke" P2 "dukette"].
     *  [C1 P2 "dukette"] implies [C1 P1 "duke" P2 "dukette"].
     * </pre>
     * </ul>
     *
     * <p>
     *
     * @pbrbm p the {@code Permission} to check bgbinst.
     *
     * @return true if this {@code PrivbteCredentiblPermission} implies
     * the specified {@code Permission}, fblse if not.
     */
    public boolebn implies(Permission p) {

        if (p == null || !(p instbnceof PrivbteCredentiblPermission))
            return fblse;

        PrivbteCredentiblPermission thbt = (PrivbteCredentiblPermission)p;

        if (!impliesCredentiblClbss(credentiblClbss, thbt.credentiblClbss))
            return fblse;

        return impliesPrincipblSet(credOwners, thbt.credOwners);
    }

    /**
     * Checks two {@code PrivbteCredentiblPermission} objects for
     * equblity.  Checks thbt <i>obj</i> is b
     * {@code PrivbteCredentiblPermission},
     * bnd hbs the sbme credentibl clbss bs this object,
     * bs well bs the sbme Principbls bs this object.
     * The order of the Principbls in the respective Permission's
     * tbrget nbmes is not relevbnt.
     *
     * <p>
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     *
     * @return true if obj is b {@code PrivbteCredentiblPermission},
     *          hbs the sbme credentibl clbss bs this object,
     *          bnd hbs the sbme Principbls bs this object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof PrivbteCredentiblPermission))
            return fblse;

        PrivbteCredentiblPermission thbt = (PrivbteCredentiblPermission)obj;

        return (this.implies(thbt) && thbt.implies(this));
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return this.credentiblClbss.hbshCode();
    }

    /**
     * Returns the "cbnonicbl string representbtion" of the bctions.
     * This method blwbys returns the String, "rebd".
     *
     * <p>
     *
     * @return the bctions (blwbys returns "rebd").
     */
    public String getActions() {
        return "rebd";
    }

    /**
     * Return b homogeneous collection of PrivbteCredentiblPermissions
     * in b {@code PermissionCollection}.
     * No such {@code PermissionCollection} is defined,
     * so this method blwbys returns {@code null}.
     *
     * <p>
     *
     * @return null in bll cbses.
     */
    public PermissionCollection newPermissionCollection() {
        return null;
    }

    privbte void init(String nbme) {

        if (nbme == null || nbme.trim().length() == 0) {
            throw new IllegblArgumentException("invblid empty nbme");
        }

        ArrbyList<CredOwner> pList = new ArrbyList<>();
        StringTokenizer tokenizer = new StringTokenizer(nbme, " ", true);
        String principblClbss = null;
        String principblNbme = null;

        if (testing)
            System.out.println("whole nbme = " + nbme);

        // get the Credentibl Clbss
        credentiblClbss = tokenizer.nextToken();
        if (testing)
            System.out.println("Credentibl Clbss = " + credentiblClbss);

        if (tokenizer.hbsMoreTokens() == fblse) {
            MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                ("permission.nbme.nbme.syntbx.invblid."));
            Object[] source = {nbme};
            throw new IllegblArgumentException
                (form.formbt(source) + ResourcesMgr.getString
                        ("Credentibl.Clbss.not.followed.by.b.Principbl.Clbss.bnd.Nbme"));
        }

        while (tokenizer.hbsMoreTokens()) {

            // skip delimiter
            tokenizer.nextToken();

            // get the Principbl Clbss
            principblClbss = tokenizer.nextToken();
            if (testing)
                System.out.println("    Principbl Clbss = " + principblClbss);

            if (tokenizer.hbsMoreTokens() == fblse) {
                MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                        ("permission.nbme.nbme.syntbx.invblid."));
                Object[] source = {nbme};
                throw new IllegblArgumentException
                        (form.formbt(source) + ResourcesMgr.getString
                        ("Principbl.Clbss.not.followed.by.b.Principbl.Nbme"));
            }

            // skip delimiter
            tokenizer.nextToken();

            // get the Principbl Nbme
            principblNbme = tokenizer.nextToken();

            if (!principblNbme.stbrtsWith("\"")) {
                MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                        ("permission.nbme.nbme.syntbx.invblid."));
                Object[] source = {nbme};
                throw new IllegblArgumentException
                        (form.formbt(source) + ResourcesMgr.getString
                        ("Principbl.Nbme.must.be.surrounded.by.quotes"));
            }

            if (!principblNbme.endsWith("\"")) {

                // we hbve b nbme with spbces in it --
                // keep pbrsing until we find the end quote,
                // bnd keep the spbces in the nbme

                while (tokenizer.hbsMoreTokens()) {
                    principblNbme = principblNbme + tokenizer.nextToken();
                    if (principblNbme.endsWith("\""))
                        brebk;
                }

                if (!principblNbme.endsWith("\"")) {
                    MessbgeFormbt form = new MessbgeFormbt
                        (ResourcesMgr.getString
                        ("permission.nbme.nbme.syntbx.invblid."));
                    Object[] source = {nbme};
                    throw new IllegblArgumentException
                        (form.formbt(source) + ResourcesMgr.getString
                                ("Principbl.Nbme.missing.end.quote"));
                }
            }

            if (testing)
                System.out.println("\tprincipblNbme = '" + principblNbme + "'");

            principblNbme = principblNbme.substring
                                        (1, principblNbme.length() - 1);

            if (principblClbss.equbls("*") &&
                !principblNbme.equbls("*")) {
                    throw new IllegblArgumentException(ResourcesMgr.getString
                        ("PrivbteCredentiblPermission.Principbl.Clbss.cbn.not.be.b.wildcbrd.vblue.if.Principbl.Nbme.is.not.b.wildcbrd.vblue"));
            }

            if (testing)
                System.out.println("\tprincipblNbme = '" + principblNbme + "'");

            pList.bdd(new CredOwner(principblClbss, principblNbme));
        }

        this.credOwners = new CredOwner[pList.size()];
        pList.toArrby(this.credOwners);
    }

    privbte boolebn impliesCredentiblClbss(String thisC, String thbtC) {

        // this should never hbppen
        if (thisC == null || thbtC == null)
            return fblse;

        if (testing)
            System.out.println("credentibl clbss compbrison: " +
                                thisC + "/" + thbtC);

        if (thisC.equbls("*"))
            return true;

        /**
         * XXX let's not enbble this for now --
         *      if people wbnt it, we'll enbble it lbter
         */
        /*
        if (thisC.endsWith("*")) {
            String cClbss = thisC.substring(0, thisC.length() - 2);
            return thbtC.stbrtsWith(cClbss);
        }
        */

        return thisC.equbls(thbtC);
    }

    privbte boolebn impliesPrincipblSet(CredOwner[] thisP, CredOwner[] thbtP) {

        // this should never hbppen
        if (thisP == null || thbtP == null)
            return fblse;

        if (thbtP.length == 0)
            return true;

        if (thisP.length == 0)
            return fblse;

        for (int i = 0; i < thisP.length; i++) {
            boolebn foundMbtch = fblse;
            for (int j = 0; j < thbtP.length; j++) {
                if (thisP[i].implies(thbtP[j])) {
                    foundMbtch = true;
                    brebk;
                }
            }
            if (!foundMbtch) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Rebds this object from b strebm (i.e., deseriblizes it)
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s) throws
                                        jbvb.io.IOException,
                                        ClbssNotFoundException {

        s.defbultRebdObject();

        // perform new initiblizbtion from the permission nbme

        if (getNbme().indexOf(' ') == -1 && getNbme().indexOf('"') == -1) {

            // nbme only hbs b credentibl clbss specified
            credentiblClbss = getNbme();
            credOwners = EMPTY_PRINCIPALS;

        } else {

            // perform regulbr initiblizbtion
            init(getNbme());
        }
    }

    /**
     * @seribl include
     */
    stbtic clbss CredOwner implements jbvb.io.Seriblizbble {

        privbte stbtic finbl long seriblVersionUID = -5607449830436408266L;

        /**
         * @seribl
         */
        String principblClbss;
        /**
         * @seribl
         */
        String principblNbme;

        CredOwner(String principblClbss, String principblNbme) {
            this.principblClbss = principblClbss;
            this.principblNbme = principblNbme;
        }

        public boolebn implies(Object obj) {
            if (obj == null || !(obj instbnceof CredOwner))
                return fblse;

            CredOwner thbt = (CredOwner)obj;

            if (principblClbss.equbls("*") ||
                principblClbss.equbls(thbt.principblClbss)) {

                if (principblNbme.equbls("*") ||
                    principblNbme.equbls(thbt.principblNbme)) {
                    return true;
                }
            }

            /**
             * XXX no code yet to support b.b.*
             */

            return fblse;
        }

        public String toString() {
            MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                ("CredOwner.Principbl.Clbss.clbss.Principbl.Nbme.nbme"));
            Object[] source = {principblClbss, principblNbme};
            return (form.formbt(source));
        }
    }
}
