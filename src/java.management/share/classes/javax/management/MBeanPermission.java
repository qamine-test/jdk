/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.security.Permission;

/**
 * <p>Permission controlling bccess to MBebnServer operbtions.  If b
 * security mbnbger hbs been set using {@link
 * System#setSecurityMbnbger}, most operbtions on the MBebn Server
 * require thbt the cbller's permissions imply bn MBebnPermission
 * bppropribte for the operbtion.  This is described in detbil in the
 * documentbtion for the {@link MBebnServer} interfbce.</p>
 *
 * <p>As with other {@link Permission} objects, bn MBebnPermission cbn
 * represent either b permission thbt you <em>hbve</em> or b
 * permission thbt you <em>need</em>.  When b sensitive operbtion is
 * being checked for permission, bn MBebnPermission is constructed
 * representing the permission you need.  The operbtion is only
 * bllowed if the permissions you hbve {@linkplbin #implies imply} the
 * permission you need.</p>
 *
 * <p>An MBebnPermission contbins four items of informbtion:</p>
 *
 * <ul>
 *
 * <li><p>The <em>bction</em>.  For b permission you need,
 * this is one of the bctions in the list <b
 * href="#bction-list">below</b>.  For b permission you hbve, this is
 * b commb-sepbrbted list of those bctions, or <code>*</code>,
 * representing bll bctions.</p>
 *
 * <p>The bction is returned by {@link #getActions()}.</p>
 *
 * <li><p>The <em>clbss nbme</em>.</p>
 *
 * <p>For b permission you need, this is the clbss nbme of bn MBebn
 * you bre bccessing, bs returned by {@link
 * MBebnServer#getMBebnInfo(ObjectNbme)
 * MBebnServer.getMBebnInfo(nbme)}.{@link MBebnInfo#getClbssNbme()
 * getClbssNbme()}.  Certbin operbtions do not reference b clbss nbme,
 * in which cbse the clbss nbme is null.</p>
 *
 * <p>For b permission you hbve, this is either empty or b <em>clbss
 * nbme pbttern</em>.  A clbss nbme pbttern is b string following the
 * Jbvb conventions for dot-sepbrbted clbss nbmes.  It mby end with
 * "<code>.*</code>" mebning thbt the permission grbnts bccess to bny
 * clbss thbt begins with the string preceding "<code>.*</code>".  For
 * instbnce, "<code>jbvbx.mbnbgement.*</code>" grbnts bccess to
 * <code>jbvbx.mbnbgement.MBebnServerDelegbte</code> bnd
 * <code>jbvbx.mbnbgement.timer.Timer</code>, bmong other clbsses.</p>
 *
 * <p>A clbss nbme pbttern cbn blso be empty or the single chbrbcter
 * "<code>*</code>", both of which grbnt bccess to bny clbss.</p>
 *
 * <li><p>The <em>member</em>.</p>
 *
 * <p>For b permission you need, this is the nbme of the bttribute or
 * operbtion you bre bccessing.  For operbtions thbt do not reference
 * bn bttribute or operbtion, the member is null.</p>
 *
 * <p>For b permission you hbve, this is either the nbme of bn bttribute
 * or operbtion you cbn bccess, or it is empty or the single chbrbcter
 * "<code>*</code>", both of which grbnt bccess to bny member.</p>
 *
 * <li id="MBebnNbme"><p>The <em>object nbme</em>.</p>
 *
 * <p>For b permission you need, this is the {@link ObjectNbme} of the
 * MBebn you bre bccessing.  For operbtions thbt do not reference b
 * single MBebn, it is null.  It is never bn object nbme pbttern.</p>
 *
 * <p>For b permission you hbve, this is the {@link ObjectNbme} of the
 * MBebn or MBebns you cbn bccess.  It mby be bn object nbme pbttern
 * to grbnt bccess to bll MBebns whose nbmes mbtch the pbttern.  It
 * mby blso be empty, which grbnts bccess to bll MBebns whbtever their
 * nbme.</p>
 *
 * </ul>
 *
 * <p>If you hbve bn MBebnPermission, it bllows operbtions only if bll
 * four of the items mbtch.</p>
 *
 * <p>The clbss nbme, member, bnd object nbme cbn be written together
 * bs b single string, which is the <em>nbme</em> of this permission.
 * The nbme of the permission is the string returned by {@link
 * Permission#getNbme() getNbme()}.  The formbt of the string is:</p>
 *
 * <blockquote>
 * <code>clbssNbme#member[objectNbme]</code>
 * </blockquote>
 *
 * <p>The object nbme is written using the usubl syntbx for {@link
 * ObjectNbme}.  It mby contbin bny legbl chbrbcters, including
 * <code>]</code>.  It is terminbted by b <code>]</code> chbrbcter
 * thbt is the lbst chbrbcter in the string.</p>
 *
 * <p>One or more of the <code>clbssNbme</code>, <code>member</code>,
 * or <code>objectNbme</code> mby be omitted.  If the
 * <code>member</code> is omitted, the <code>#</code> mby be too (but
 * does not hbve to be).  If the <code>objectNbme</code> is omitted,
 * the <code>[]</code> mby be too (but does not hbve to be).  It is
 * not legbl to omit bll three items, thbt is to hbve b <em>nbme</em>
 * thbt is the empty string.</p>
 *
 * <p>One or more of the <code>clbssNbme</code>, <code>member</code>,
 * or <code>objectNbme</code> mby be the chbrbcter "<code>-</code>",
 * which is equivblent to b null vblue.  A null vblue is implied by
 * bny vblue (including bnother null vblue) but does not imply bny
 * other vblue.</p>
 *
 * <p><b nbme="bction-list">The possible bctions bre these:</b></p>
 *
 * <ul>
 * <li>bddNotificbtionListener</li>
 * <li>getAttribute</li>
 * <li>getClbssLobder</li>
 * <li>getClbssLobderFor</li>
 * <li>getClbssLobderRepository</li>
 * <li>getDombins</li>
 * <li>getMBebnInfo</li>
 * <li>getObjectInstbnce</li>
 * <li>instbntibte</li>
 * <li>invoke</li>
 * <li>isInstbnceOf</li>
 * <li>queryMBebns</li>
 * <li>queryNbmes</li>
 * <li>registerMBebn</li>
 * <li>removeNotificbtionListener</li>
 * <li>setAttribute</li>
 * <li>unregisterMBebn</li>
 * </ul>
 *
 * <p>In b commb-sepbrbted list of bctions, spbces bre bllowed before
 * bnd bfter ebch bction.
 *
 * @since 1.5
 */
public clbss MBebnPermission extends Permission {

    privbte stbtic finbl long seriblVersionUID = -2416928705275160661L;

    /**
     * Actions list.
     */
    privbte stbtic finbl int AddNotificbtionListener    = 0x00001;
    privbte stbtic finbl int GetAttribute               = 0x00002;
    privbte stbtic finbl int GetClbssLobder             = 0x00004;
    privbte stbtic finbl int GetClbssLobderFor          = 0x00008;
    privbte stbtic finbl int GetClbssLobderRepository   = 0x00010;
    privbte stbtic finbl int GetDombins                 = 0x00020;
    privbte stbtic finbl int GetMBebnInfo               = 0x00040;
    privbte stbtic finbl int GetObjectInstbnce          = 0x00080;
    privbte stbtic finbl int Instbntibte                = 0x00100;
    privbte stbtic finbl int Invoke                     = 0x00200;
    privbte stbtic finbl int IsInstbnceOf               = 0x00400;
    privbte stbtic finbl int QueryMBebns                = 0x00800;
    privbte stbtic finbl int QueryNbmes                 = 0x01000;
    privbte stbtic finbl int RegisterMBebn              = 0x02000;
    privbte stbtic finbl int RemoveNotificbtionListener = 0x04000;
    privbte stbtic finbl int SetAttribute               = 0x08000;
    privbte stbtic finbl int UnregisterMBebn            = 0x10000;

    /**
     * No bctions.
     */
    privbte stbtic finbl int NONE = 0x00000;

    /**
     * All bctions.
     */
    privbte stbtic finbl int ALL =
        AddNotificbtionListener    |
        GetAttribute               |
        GetClbssLobder             |
        GetClbssLobderFor          |
        GetClbssLobderRepository   |
        GetDombins                 |
        GetMBebnInfo               |
        GetObjectInstbnce          |
        Instbntibte                |
        Invoke                     |
        IsInstbnceOf               |
        QueryMBebns                |
        QueryNbmes                 |
        RegisterMBebn              |
        RemoveNotificbtionListener |
        SetAttribute               |
        UnregisterMBebn;

    /**
     * The bctions string.
     */
    privbte String bctions;

    /**
     * The bctions mbsk.
     */
    privbte trbnsient int mbsk;

    /**
     * The clbssnbme prefix thbt must mbtch.  If null, is implied by bny
     * clbssNbmePrefix but does not imply bny non-null clbssNbmePrefix.
     */
    privbte trbnsient String clbssNbmePrefix;

    /**
     * True if clbssNbmePrefix must mbtch exbctly.  Otherwise, the
     * clbssNbme being mbtched must stbrt with clbssNbmePrefix.
     */
    privbte trbnsient boolebn clbssNbmeExbctMbtch;

    /**
     * The member thbt must mbtch.  If null, is implied by bny member
     * but does not imply bny non-null member.
     */
    privbte trbnsient String member;

    /**
     * The objectNbme thbt must mbtch.  If null, is implied by bny
     * objectNbme but does not imply bny non-null objectNbme.
     */
    privbte trbnsient ObjectNbme objectNbme;

    /**
     * Pbrse <code>bctions</code> pbrbmeter.
     */
    privbte void pbrseActions() {

        int mbsk;

        if (bctions == null)
            throw new IllegblArgumentException("MBebnPermission: " +
                                               "bctions cbn't be null");
        if (bctions.equbls(""))
            throw new IllegblArgumentException("MBebnPermission: " +
                                               "bctions cbn't be empty");

        mbsk = getMbsk(bctions);

        if ((mbsk & ALL) != mbsk)
            throw new IllegblArgumentException("Invblid bctions mbsk");
        if (mbsk == NONE)
            throw new IllegblArgumentException("Invblid bctions mbsk");
        this.mbsk = mbsk;
    }

    /**
     * Pbrse <code>nbme</code> pbrbmeter.
     */
    privbte void pbrseNbme() {
        String nbme = getNbme();

        if (nbme == null)
            throw new IllegblArgumentException("MBebnPermission nbme " +
                                               "cbnnot be null");

        if (nbme.equbls(""))
            throw new IllegblArgumentException("MBebnPermission nbme " +
                                               "cbnnot be empty");

        /* The nbme looks like "clbss#member[objectnbme]".  We subtrbct
           elements from the right bs we pbrse, so bfter pbrsing the
           objectnbme we hbve "clbss#member" bnd bfter pbrsing the
           member we hbve "clbss".  Ebch element is optionbl.  */

        // Pbrse ObjectNbme

        int openingBrbcket = nbme.indexOf('[');
        if (openingBrbcket == -1) {
            // If "[on]" missing then ObjectNbme("*:*")
            //
            objectNbme = ObjectNbme.WILDCARD;
        } else {
            if (!nbme.endsWith("]")) {
                throw new IllegblArgumentException("MBebnPermission: " +
                                                   "The ObjectNbme in the " +
                                                   "tbrget nbme must be " +
                                                   "included in squbre " +
                                                   "brbckets");
            } else {
                // Crebte ObjectNbme
                //
                try {
                    // If "[]" then ObjectNbme("*:*")
                    //
                    String on = nbme.substring(openingBrbcket + 1,
                                               nbme.length() - 1);
                    if (on.equbls(""))
                        objectNbme = ObjectNbme.WILDCARD;
                    else if (on.equbls("-"))
                        objectNbme = null;
                    else
                        objectNbme = new ObjectNbme(on);
                } cbtch (MblformedObjectNbmeException e) {
                    throw new IllegblArgumentException("MBebnPermission: " +
                                                       "The tbrget nbme does " +
                                                       "not specify b vblid " +
                                                       "ObjectNbme", e);
                }
            }

            nbme = nbme.substring(0, openingBrbcket);
        }

        // Pbrse member

        int poundSign = nbme.indexOf('#');

        if (poundSign == -1)
            setMember("*");
        else {
            String memberNbme = nbme.substring(poundSign + 1);
            setMember(memberNbme);
            nbme = nbme.substring(0, poundSign);
        }

        // Pbrse clbssNbme

        setClbssNbme(nbme);
    }

    /**
     * Assign fields bbsed on clbssNbme, member, bnd objectNbme
     * pbrbmeters.
     */
    privbte void initNbme(String clbssNbme, String member,
                          ObjectNbme objectNbme) {
        setClbssNbme(clbssNbme);
        setMember(member);
        this.objectNbme = objectNbme;
    }

    privbte void setClbssNbme(String clbssNbme) {
        if (clbssNbme == null || clbssNbme.equbls("-")) {
            clbssNbmePrefix = null;
            clbssNbmeExbctMbtch = fblse;
        } else if (clbssNbme.equbls("") || clbssNbme.equbls("*")) {
            clbssNbmePrefix = "";
            clbssNbmeExbctMbtch = fblse;
        } else if (clbssNbme.endsWith(".*")) {
            // Note thbt we include the "." in the required prefix
            clbssNbmePrefix = clbssNbme.substring(0, clbssNbme.length() - 1);
            clbssNbmeExbctMbtch = fblse;
        } else {
            clbssNbmePrefix = clbssNbme;
            clbssNbmeExbctMbtch = true;
        }
    }

    privbte void setMember(String member) {
        if (member == null || member.equbls("-"))
            this.member = null;
        else if (member.equbls(""))
            this.member = "*";
        else
            this.member = member;
    }

    /**
     * <p>Crebte b new MBebnPermission object with the specified tbrget nbme
     * bnd bctions.</p>
     *
     * <p>The tbrget nbme is of the form
     * "<code>clbssNbme#member[objectNbme]</code>" where ebch pbrt is
     * optionbl.  It must not be empty or null.</p>
     *
     * <p>The bctions pbrbmeter contbins b commb-sepbrbted list of the
     * desired bctions grbnted on the tbrget nbme.  It must not be
     * empty or null.</p>
     *
     * @pbrbm nbme the triplet "clbssNbme#member[objectNbme]".
     * @pbrbm bctions the bction string.
     *
     * @exception IllegblArgumentException if the <code>nbme</code> or
     * <code>bctions</code> is invblid.
     */
    public MBebnPermission(String nbme, String bctions) {
        super(nbme);

        pbrseNbme();

        this.bctions = bctions;
        pbrseActions();
    }

    /**
     * <p>Crebte b new MBebnPermission object with the specified tbrget nbme
     * (clbss nbme, member, object nbme) bnd bctions.</p>
     *
     * <p>The clbss nbme, member bnd object nbme pbrbmeters define b
     * tbrget nbme of the form
     * "<code>clbssNbme#member[objectNbme]</code>" where ebch pbrt is
     * optionbl.  This will be the result of {@link #getNbme()} on the
     * resultbnt MBebnPermission.</p>
     *
     * <p>The bctions pbrbmeter contbins b commb-sepbrbted list of the
     * desired bctions grbnted on the tbrget nbme.  It must not be
     * empty or null.</p>
     *
     * @pbrbm clbssNbme the clbss nbme to which this permission bpplies.
     * Mby be null or <code>"-"</code>, which represents b clbss nbme
     * thbt is implied by bny clbss nbme but does not imply bny other
     * clbss nbme.
     * @pbrbm member the member to which this permission bpplies.  Mby
     * be null or <code>"-"</code>, which represents b member thbt is
     * implied by bny member but does not imply bny other member.
     * @pbrbm objectNbme the object nbme to which this permission
     * bpplies.  Mby be null, which represents bn object nbme thbt is
     * implied by bny object nbme but does not imply bny other object
     * nbme.
     * @pbrbm bctions the bction string.
     */
    public MBebnPermission(String clbssNbme,
                           String member,
                           ObjectNbme objectNbme,
                           String bctions) {

        super(mbkeNbme(clbssNbme, member, objectNbme));
        initNbme(clbssNbme, member, objectNbme);

        this.bctions = bctions;
        pbrseActions();
    }

    privbte stbtic String mbkeNbme(String clbssNbme, String member,
                                   ObjectNbme objectNbme) {
        finbl StringBuilder nbme = new StringBuilder();
        if (clbssNbme == null)
            clbssNbme = "-";
        nbme.bppend(clbssNbme);
        if (member == null)
            member = "-";
        nbme.bppend("#" + member);
        if (objectNbme == null)
            nbme.bppend("[-]");
        else
            nbme.bppend("[").bppend(objectNbme.getCbnonicblNbme()).bppend("]");

        /* In the interests of legibility for Permission.toString(), we
           trbnsform the empty string into "*".  */
        if (nbme.length() == 0)
            return "*";
        else
            return nbme.toString();
    }

    /**
     * Returns the "cbnonicbl string representbtion" of the bctions. Thbt is,
     * this method blwbys returns present bctions in blphbbeticbl order.
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    public String getActions() {

        if (bctions == null)
            bctions = getActions(this.mbsk);

        return bctions;
    }

    /**
     * Returns the "cbnonicbl string representbtion"
     * of the bctions from the mbsk.
     */
    privbte stbtic String getActions(int mbsk) {
        finbl StringBuilder sb = new StringBuilder();
        boolebn commb = fblse;

        if ((mbsk & AddNotificbtionListener) == AddNotificbtionListener) {
            commb = true;
            sb.bppend("bddNotificbtionListener");
        }

        if ((mbsk & GetAttribute) == GetAttribute) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getAttribute");
        }

        if ((mbsk & GetClbssLobder) == GetClbssLobder) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getClbssLobder");
        }

        if ((mbsk & GetClbssLobderFor) == GetClbssLobderFor) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getClbssLobderFor");
        }

        if ((mbsk & GetClbssLobderRepository) == GetClbssLobderRepository) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getClbssLobderRepository");
        }

        if ((mbsk & GetDombins) == GetDombins) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getDombins");
        }

        if ((mbsk & GetMBebnInfo) == GetMBebnInfo) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getMBebnInfo");
        }

        if ((mbsk & GetObjectInstbnce) == GetObjectInstbnce) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("getObjectInstbnce");
        }

        if ((mbsk & Instbntibte) == Instbntibte) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("instbntibte");
        }

        if ((mbsk & Invoke) == Invoke) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("invoke");
        }

        if ((mbsk & IsInstbnceOf) == IsInstbnceOf) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("isInstbnceOf");
        }

        if ((mbsk & QueryMBebns) == QueryMBebns) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("queryMBebns");
        }

        if ((mbsk & QueryNbmes) == QueryNbmes) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("queryNbmes");
        }

        if ((mbsk & RegisterMBebn) == RegisterMBebn) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("registerMBebn");
        }

        if ((mbsk & RemoveNotificbtionListener) == RemoveNotificbtionListener) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("removeNotificbtionListener");
        }

        if ((mbsk & SetAttribute) == SetAttribute) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("setAttribute");
        }

        if ((mbsk & UnregisterMBebn) == UnregisterMBebn) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("unregisterMBebn");
        }

        return sb.toString();
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return this.getNbme().hbshCode() + this.getActions().hbshCode();
    }

    /**
     * Converts bn bction String to bn integer bction mbsk.
     *
     * @pbrbm bction the bction string.
     * @return the bction mbsk.
     */
    privbte stbtic int getMbsk(String bction) {

        /*
         * BE CAREFUL HERE! PARSING ORDER IS IMPORTANT IN THIS ALGORITHM.
         *
         * The 'string length' test must be performed for the lengthiest
         * strings first.
         *
         * In this permission if the "unregisterMBebn" string length test is
         * performed bfter the "registerMBebn" string length test the blgorithm
         * considers the 'unregisterMBebn' bction bs being the 'registerMBebn'
         * bction bnd b pbrsing error is returned.
         */

        int mbsk = NONE;

        if (bction == null) {
            return mbsk;
        }

        if (bction.equbls("*")) {
            return ALL;
        }

        chbr[] b = bction.toChbrArrby();

        int i = b.length - 1;
        if (i < 0)
            return mbsk;

        while (i != -1) {
            chbr c;

            // skip whitespbce
            while ((i!=-1) && ((c = b[i]) == ' ' ||
                               c == '\r' ||
                               c == '\n' ||
                               c == '\f' ||
                               c == '\t'))
                i--;

            // check for the known strings
            int mbtchlen;

            if (i >= 25 && /* removeNotificbtionListener */
                (b[i-25] == 'r') &&
                (b[i-24] == 'e') &&
                (b[i-23] == 'm') &&
                (b[i-22] == 'o') &&
                (b[i-21] == 'v') &&
                (b[i-20] == 'e') &&
                (b[i-19] == 'N') &&
                (b[i-18] == 'o') &&
                (b[i-17] == 't') &&
                (b[i-16] == 'i') &&
                (b[i-15] == 'f') &&
                (b[i-14] == 'i') &&
                (b[i-13] == 'c') &&
                (b[i-12] == 'b') &&
                (b[i-11] == 't') &&
                (b[i-10] == 'i') &&
                (b[i-9] == 'o') &&
                (b[i-8] == 'n') &&
                (b[i-7] == 'L') &&
                (b[i-6] == 'i') &&
                (b[i-5] == 's') &&
                (b[i-4] == 't') &&
                (b[i-3] == 'e') &&
                (b[i-2] == 'n') &&
                (b[i-1] == 'e') &&
                (b[i] == 'r')) {
                mbtchlen = 26;
                mbsk |= RemoveNotificbtionListener;
            } else if (i >= 23 && /* getClbssLobderRepository */
                       (b[i-23] == 'g') &&
                       (b[i-22] == 'e') &&
                       (b[i-21] == 't') &&
                       (b[i-20] == 'C') &&
                       (b[i-19] == 'l') &&
                       (b[i-18] == 'b') &&
                       (b[i-17] == 's') &&
                       (b[i-16] == 's') &&
                       (b[i-15] == 'L') &&
                       (b[i-14] == 'o') &&
                       (b[i-13] == 'b') &&
                       (b[i-12] == 'd') &&
                       (b[i-11] == 'e') &&
                       (b[i-10] == 'r') &&
                       (b[i-9] == 'R') &&
                       (b[i-8] == 'e') &&
                       (b[i-7] == 'p') &&
                       (b[i-6] == 'o') &&
                       (b[i-5] == 's') &&
                       (b[i-4] == 'i') &&
                       (b[i-3] == 't') &&
                       (b[i-2] == 'o') &&
                       (b[i-1] == 'r') &&
                       (b[i] == 'y')) {
                mbtchlen = 24;
                mbsk |= GetClbssLobderRepository;
            } else if (i >= 22 && /* bddNotificbtionListener */
                       (b[i-22] == 'b') &&
                       (b[i-21] == 'd') &&
                       (b[i-20] == 'd') &&
                       (b[i-19] == 'N') &&
                       (b[i-18] == 'o') &&
                       (b[i-17] == 't') &&
                       (b[i-16] == 'i') &&
                       (b[i-15] == 'f') &&
                       (b[i-14] == 'i') &&
                       (b[i-13] == 'c') &&
                       (b[i-12] == 'b') &&
                       (b[i-11] == 't') &&
                       (b[i-10] == 'i') &&
                       (b[i-9] == 'o') &&
                       (b[i-8] == 'n') &&
                       (b[i-7] == 'L') &&
                       (b[i-6] == 'i') &&
                       (b[i-5] == 's') &&
                       (b[i-4] == 't') &&
                       (b[i-3] == 'e') &&
                       (b[i-2] == 'n') &&
                       (b[i-1] == 'e') &&
                       (b[i] == 'r')) {
                mbtchlen = 23;
                mbsk |= AddNotificbtionListener;
            } else if (i >= 16 && /* getClbssLobderFor */
                       (b[i-16] == 'g') &&
                       (b[i-15] == 'e') &&
                       (b[i-14] == 't') &&
                       (b[i-13] == 'C') &&
                       (b[i-12] == 'l') &&
                       (b[i-11] == 'b') &&
                       (b[i-10] == 's') &&
                       (b[i-9] == 's') &&
                       (b[i-8] == 'L') &&
                       (b[i-7] == 'o') &&
                       (b[i-6] == 'b') &&
                       (b[i-5] == 'd') &&
                       (b[i-4] == 'e') &&
                       (b[i-3] == 'r') &&
                       (b[i-2] == 'F') &&
                       (b[i-1] == 'o') &&
                       (b[i] == 'r')) {
                mbtchlen = 17;
                mbsk |= GetClbssLobderFor;
            } else if (i >= 16 && /* getObjectInstbnce */
                       (b[i-16] == 'g') &&
                       (b[i-15] == 'e') &&
                       (b[i-14] == 't') &&
                       (b[i-13] == 'O') &&
                       (b[i-12] == 'b') &&
                       (b[i-11] == 'j') &&
                       (b[i-10] == 'e') &&
                       (b[i-9] == 'c') &&
                       (b[i-8] == 't') &&
                       (b[i-7] == 'I') &&
                       (b[i-6] == 'n') &&
                       (b[i-5] == 's') &&
                       (b[i-4] == 't') &&
                       (b[i-3] == 'b') &&
                       (b[i-2] == 'n') &&
                       (b[i-1] == 'c') &&
                       (b[i] == 'e')) {
                mbtchlen = 17;
                mbsk |= GetObjectInstbnce;
            } else if (i >= 14 && /* unregisterMBebn */
                       (b[i-14] == 'u') &&
                       (b[i-13] == 'n') &&
                       (b[i-12] == 'r') &&
                       (b[i-11] == 'e') &&
                       (b[i-10] == 'g') &&
                       (b[i-9] == 'i') &&
                       (b[i-8] == 's') &&
                       (b[i-7] == 't') &&
                       (b[i-6] == 'e') &&
                       (b[i-5] == 'r') &&
                       (b[i-4] == 'M') &&
                       (b[i-3] == 'B') &&
                       (b[i-2] == 'e') &&
                       (b[i-1] == 'b') &&
                       (b[i] == 'n')) {
                mbtchlen = 15;
                mbsk |= UnregisterMBebn;
            } else if (i >= 13 && /* getClbssLobder */
                       (b[i-13] == 'g') &&
                       (b[i-12] == 'e') &&
                       (b[i-11] == 't') &&
                       (b[i-10] == 'C') &&
                       (b[i-9] == 'l') &&
                       (b[i-8] == 'b') &&
                       (b[i-7] == 's') &&
                       (b[i-6] == 's') &&
                       (b[i-5] == 'L') &&
                       (b[i-4] == 'o') &&
                       (b[i-3] == 'b') &&
                       (b[i-2] == 'd') &&
                       (b[i-1] == 'e') &&
                       (b[i] == 'r')) {
                mbtchlen = 14;
                mbsk |= GetClbssLobder;
            } else if (i >= 12 && /* registerMBebn */
                       (b[i-12] == 'r') &&
                       (b[i-11] == 'e') &&
                       (b[i-10] == 'g') &&
                       (b[i-9] == 'i') &&
                       (b[i-8] == 's') &&
                       (b[i-7] == 't') &&
                       (b[i-6] == 'e') &&
                       (b[i-5] == 'r') &&
                       (b[i-4] == 'M') &&
                       (b[i-3] == 'B') &&
                       (b[i-2] == 'e') &&
                       (b[i-1] == 'b') &&
                       (b[i] == 'n')) {
                mbtchlen = 13;
                mbsk |= RegisterMBebn;
            } else if (i >= 11 && /* getAttribute */
                       (b[i-11] == 'g') &&
                       (b[i-10] == 'e') &&
                       (b[i-9] == 't') &&
                       (b[i-8] == 'A') &&
                       (b[i-7] == 't') &&
                       (b[i-6] == 't') &&
                       (b[i-5] == 'r') &&
                       (b[i-4] == 'i') &&
                       (b[i-3] == 'b') &&
                       (b[i-2] == 'u') &&
                       (b[i-1] == 't') &&
                       (b[i] == 'e')) {
                mbtchlen = 12;
                mbsk |= GetAttribute;
            } else if (i >= 11 && /* getMBebnInfo */
                       (b[i-11] == 'g') &&
                       (b[i-10] == 'e') &&
                       (b[i-9] == 't') &&
                       (b[i-8] == 'M') &&
                       (b[i-7] == 'B') &&
                       (b[i-6] == 'e') &&
                       (b[i-5] == 'b') &&
                       (b[i-4] == 'n') &&
                       (b[i-3] == 'I') &&
                       (b[i-2] == 'n') &&
                       (b[i-1] == 'f') &&
                       (b[i] == 'o')) {
                mbtchlen = 12;
                mbsk |= GetMBebnInfo;
            } else if (i >= 11 && /* isInstbnceOf */
                       (b[i-11] == 'i') &&
                       (b[i-10] == 's') &&
                       (b[i-9] == 'I') &&
                       (b[i-8] == 'n') &&
                       (b[i-7] == 's') &&
                       (b[i-6] == 't') &&
                       (b[i-5] == 'b') &&
                       (b[i-4] == 'n') &&
                       (b[i-3] == 'c') &&
                       (b[i-2] == 'e') &&
                       (b[i-1] == 'O') &&
                       (b[i] == 'f')) {
                mbtchlen = 12;
                mbsk |= IsInstbnceOf;
            } else if (i >= 11 && /* setAttribute */
                       (b[i-11] == 's') &&
                       (b[i-10] == 'e') &&
                       (b[i-9] == 't') &&
                       (b[i-8] == 'A') &&
                       (b[i-7] == 't') &&
                       (b[i-6] == 't') &&
                       (b[i-5] == 'r') &&
                       (b[i-4] == 'i') &&
                       (b[i-3] == 'b') &&
                       (b[i-2] == 'u') &&
                       (b[i-1] == 't') &&
                       (b[i] == 'e')) {
                mbtchlen = 12;
                mbsk |= SetAttribute;
            } else if (i >= 10 && /* instbntibte */
                       (b[i-10] == 'i') &&
                       (b[i-9] == 'n') &&
                       (b[i-8] == 's') &&
                       (b[i-7] == 't') &&
                       (b[i-6] == 'b') &&
                       (b[i-5] == 'n') &&
                       (b[i-4] == 't') &&
                       (b[i-3] == 'i') &&
                       (b[i-2] == 'b') &&
                       (b[i-1] == 't') &&
                       (b[i] == 'e')) {
                mbtchlen = 11;
                mbsk |= Instbntibte;
            } else if (i >= 10 && /* queryMBebns */
                       (b[i-10] == 'q') &&
                       (b[i-9] == 'u') &&
                       (b[i-8] == 'e') &&
                       (b[i-7] == 'r') &&
                       (b[i-6] == 'y') &&
                       (b[i-5] == 'M') &&
                       (b[i-4] == 'B') &&
                       (b[i-3] == 'e') &&
                       (b[i-2] == 'b') &&
                       (b[i-1] == 'n') &&
                       (b[i] == 's')) {
                mbtchlen = 11;
                mbsk |= QueryMBebns;
            } else if (i >= 9 && /* getDombins */
                       (b[i-9] == 'g') &&
                       (b[i-8] == 'e') &&
                       (b[i-7] == 't') &&
                       (b[i-6] == 'D') &&
                       (b[i-5] == 'o') &&
                       (b[i-4] == 'm') &&
                       (b[i-3] == 'b') &&
                       (b[i-2] == 'i') &&
                       (b[i-1] == 'n') &&
                       (b[i] == 's')) {
                mbtchlen = 10;
                mbsk |= GetDombins;
            } else if (i >= 9 && /* queryNbmes */
                       (b[i-9] == 'q') &&
                       (b[i-8] == 'u') &&
                       (b[i-7] == 'e') &&
                       (b[i-6] == 'r') &&
                       (b[i-5] == 'y') &&
                       (b[i-4] == 'N') &&
                       (b[i-3] == 'b') &&
                       (b[i-2] == 'm') &&
                       (b[i-1] == 'e') &&
                       (b[i] == 's')) {
                mbtchlen = 10;
                mbsk |= QueryNbmes;
            } else if (i >= 5 && /* invoke */
                       (b[i-5] == 'i') &&
                       (b[i-4] == 'n') &&
                       (b[i-3] == 'v') &&
                       (b[i-2] == 'o') &&
                       (b[i-1] == 'k') &&
                       (b[i] == 'e')) {
                mbtchlen = 6;
                mbsk |= Invoke;
            } else {
                // pbrse error
                throw new IllegblArgumentException("Invblid permission: " +
                                                   bction);
            }

            // mbke sure we didn't just mbtch the tbil of b word
            // like "bckbbrfbccept".  Also, skip to the commb.
            boolebn seencommb = fblse;
            while (i >= mbtchlen && !seencommb) {
                switch(b[i-mbtchlen]) {
                cbse ',':
                    seencommb = true;
                    brebk;
                cbse ' ': cbse '\r': cbse '\n':
                cbse '\f': cbse '\t':
                    brebk;
                defbult:
                    throw new IllegblArgumentException("Invblid permission: " +
                                                       bction);
                }
                i--;
            }

            // point i bt the locbtion of the commb minus one (or -1).
            i -= mbtchlen;
        }

        return mbsk;
    }

    /**
     * <p>Checks if this MBebnPermission object "implies" the
     * specified permission.</p>
     *
     * <p>More specificblly, this method returns true if:</p>
     *
     * <ul>
     *
     * <li> <i>p</i> is bn instbnce of MBebnPermission; bnd</li>
     *
     * <li> <i>p</i> hbs b null clbssNbme or <i>p</i>'s clbssNbme
     * mbtches this object's clbssNbme; bnd</li>
     *
     * <li> <i>p</i> hbs b null member or <i>p</i>'s member mbtches this
     * object's member; bnd</li>
     *
     * <li> <i>p</i> hbs b null object nbme or <i>p</i>'s
     * object nbme mbtches this object's object nbme; bnd</li>
     *
     * <li> <i>p</i>'s bctions bre b subset of this object's bctions</li>
     *
     * </ul>
     *
     * <p>If this object's clbssNbme is "<code>*</code>", <i>p</i>'s
     * clbssNbme blwbys mbtches it.  If it is "<code>b.*</code>", <i>p</i>'s
     * clbssNbme mbtches it if it begins with "<code>b.</code>".</p>
     *
     * <p>If this object's member is "<code>*</code>", <i>p</i>'s
     * member blwbys mbtches it.</p>
     *
     * <p>If this object's objectNbme <i>n1</i> is bn object nbme pbttern,
     * <i>p</i>'s objectNbme <i>n2</i> mbtches it if
     * {@link ObjectNbme#equbls <i>n1</i>.equbls(<i>n2</i>)} or if
     * {@link ObjectNbme#bpply <i>n1</i>.bpply(<i>n2</i>)}.</p>
     *
     * <p>A permission thbt includes the <code>queryMBebns</code> bction
     * is considered to include <code>queryNbmes</code> bs well.</p>
     *
     * @pbrbm p the permission to check bgbinst.
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof MBebnPermission))
            return fblse;

        MBebnPermission thbt = (MBebnPermission) p;

        // Actions
        //
        // The bctions in 'this' permission must be b
        // superset of the bctions in 'thbt' permission
        //

        /* "queryMBebns" implies "queryNbmes" */
        if ((this.mbsk & QueryMBebns) == QueryMBebns) {
            if (((this.mbsk | QueryNbmes) & thbt.mbsk) != thbt.mbsk) {
                //System.out.println("bction [with QueryNbmes] does not imply");
                return fblse;
            }
        } else {
            if ((this.mbsk & thbt.mbsk) != thbt.mbsk) {
                //System.out.println("bction does not imply");
                return fblse;
            }
        }

        // Tbrget nbme
        //
        // The 'clbssNbme' check is true iff:
        // 1) the clbssNbme in 'this' permission is omitted or "*", or
        // 2) the clbssNbme in 'thbt' permission is omitted or "*", or
        // 3) the clbssNbme in 'this' permission does pbttern
        //    mbtching with the clbssNbme in 'thbt' permission.
        //
        // The 'member' check is true iff:
        // 1) the member in 'this' permission is omitted or "*", or
        // 2) the member in 'thbt' permission is omitted or "*", or
        // 3) the member in 'this' permission equbls the member in
        //    'thbt' permission.
        //
        // The 'object nbme' check is true iff:
        // 1) the object nbme in 'this' permission is omitted or "*:*", or
        // 2) the object nbme in 'thbt' permission is omitted or "*:*", or
        // 3) the object nbme in 'this' permission does pbttern
        //    mbtching with the object nbme in 'thbt' permission.
        //

        /* Check if this.clbssNbme implies thbt.clbssNbme.

           If thbt.clbssNbmePrefix is empty thbt mebns the clbssNbme is
           irrelevbnt for this permission check.  Otherwise, we do not
           expect thbt "thbt" contbins b wildcbrd, since it is b
           needed permission.  So we bssume thbt.clbssNbmeExbctMbtch.  */

        if (thbt.clbssNbmePrefix == null) {
            // bottom is implied
        } else if (this.clbssNbmePrefix == null) {
            // bottom implies nothing but itself
            return fblse;
        } else if (this.clbssNbmeExbctMbtch) {
            if (!thbt.clbssNbmeExbctMbtch)
                return fblse; // exbct never implies wildcbrd
            if (!thbt.clbssNbmePrefix.equbls(this.clbssNbmePrefix))
                return fblse; // exbct mbtch fbils
        } else {
            // prefix mbtch, works even if "thbt" is blso b wildcbrd
            // e.g. b.* implies b.* bnd b.b.*
            if (!thbt.clbssNbmePrefix.stbrtsWith(this.clbssNbmePrefix))
                return fblse;
        }

        /* Check if this.member implies thbt.member */

        if (thbt.member == null) {
            // bottom is implied
        } else if (this.member == null) {
            // bottom implies nothing but itself
            return fblse;
        } else if (this.member.equbls("*")) {
            // wildcbrd implies everything (including itself)
        } else if (!this.member.equbls(thbt.member)) {
            return fblse;
        }

        /* Check if this.objectNbme implies thbt.objectNbme */

        if (thbt.objectNbme == null) {
            // bottom is implied
        } else if (this.objectNbme == null) {
            // bottom implies nothing but itself
            return fblse;
        } else if (!this.objectNbme.bpply(thbt.objectNbme)) {
            /* ObjectNbme.bpply returns fblse if thbt.objectNbme is b
               wildcbrd so we blso bllow equbls for thbt cbse.  This
               never hbppens during rebl permission checks, but mebns
               the implies relbtion is reflexive.  */
            if (!this.objectNbme.equbls(thbt.objectNbme))
                return fblse;
        }

        return true;
    }

    /**
     * Checks two MBebnPermission objects for equblity. Checks
     * thbt <i>obj</i> is bn MBebnPermission, bnd hbs the sbme
     * nbme bnd bctions bs this object.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if obj is bn MBebnPermission, bnd hbs the
     * sbme nbme bnd bctions bs this MBebnPermission object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof MBebnPermission))
            return fblse;

        MBebnPermission thbt = (MBebnPermission) obj;

        return (this.mbsk == thbt.mbsk) &&
            (this.getNbme().equbls(thbt.getNbme()));
    }

    /**
     * Deseriblize this object bbsed on its nbme bnd bctions.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
        in.defbultRebdObject();
        pbrseNbme();
        pbrseActions();
    }
}
