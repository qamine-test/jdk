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

pbckbge org.ietf.jgss;

import sun.security.jgss.spi.*;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

/**
 * This interfbce encbpsulbtes b single GSS-API principbl entity. The
 * bpplicbtion obtbins bn implementbtion of this interfbce
 * through one of the <code>crebteNbme</code> methods thbt exist in the {@link
 * GSSMbnbger GSSMbnbger} clbss. Conceptublly b GSSNbme contbins mbny
 * representbtions of the entity or mbny primitive nbme elements, one for
 * ebch supported underlying mechbnism. In GSS terminology, b GSSNbme thbt
 * contbins bn element from just one mechbnism is cblled b Mechbnism Nbme
 * (MN)<p>
 *
 * Since different buthenticbtion mechbnisms mby employ different
 * nbmespbces for identifying their principbls, GSS-API's nbming support is
 * necessbrily complex in multi-mechbnism environments (or even in some
 * single-mechbnism environments where the underlying mechbnism supports
 * multiple nbmespbces). Different nbme formbts bnd their definitions bre
 * identified with {@link Oid Oid's} bnd some stbndbrd types
 * bre defined in this interfbce. The formbt of the nbmes cbn be derived
 * bbsed on the unique <code>Oid</code> of its nbme type.<p>
 *
 * Included below bre code exbmples utilizing the <code>GSSNbme</code> interfbce.
 * The code below crebtes b <code>GSSNbme</code>, converts it to bn MN, performs b
 * compbrison, obtbins b printbble representbtion of the nbme, exports it
 * to b byte brrby bnd then re-imports to obtbin b
 * new <code>GSSNbme</code>.<p>
 * <pre>
 *      GSSMbnbger mbnbger = GSSMbnbger.getInstbnce();
 *
 *      // crebte b host bbsed service nbme
 *      GSSNbme nbme = mbnbger.crebteNbme("service@host",
 *                   GSSNbme.NT_HOSTBASED_SERVICE);
 *
 *      Oid krb5 = new Oid("1.2.840.113554.1.2.2");
 *
 *      GSSNbme mechNbme = nbme.cbnonicblize(krb5);
 *
 *      // the bbove two steps bre equivblent to the following
 *      GSSNbme mechNbme = mbnbger.crebteNbme("service@host",
 *                      GSSNbme.NT_HOSTBASED_SERVICE, krb5);
 *
 *      // perform nbme compbrison
 *      if (nbme.equbls(mechNbme))
 *              print("Nbmes bre equbls.");
 *
 *      // obtbin textubl representbtion of nbme bnd its printbble
 *      // nbme type
 *      print(mechNbme.toString() +
 *                      mechNbme.getStringNbmeType().toString());
 *
 *      // export bnd re-import the nbme
 *      byte [] exportNbme = mechNbme.export();
 *
 *      // crebte b new nbme object from the exported buffer
 *      GSSNbme newNbme = mbnbger.crebteNbme(exportNbme,
 *                      GSSNbme.NT_EXPORT_NAME);
 *
 * </pre>
 * @see #export()
 * @see #equbls(GSSNbme)
 * @see GSSMbnbger#crebteNbme(String, Oid)
 * @see GSSMbnbger#crebteNbme(String, Oid, Oid)
 * @see GSSMbnbger#crebteNbme(byte[], Oid)
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public interfbce GSSNbme {

    /**
     * Oid indicbting b host-bbsed service nbme form.  It is used to
     * represent services bssocibted with host computers.  This nbme form
     * is constructed using two elements, "service" bnd "hostnbme", bs
     * follows: service@hostnbme.<p>
     *
     * It represents the following Oid vblue:<br>
     *  <code>{ iso(1) member-body(2) United
     * Stbtes(840) mit(113554) infosys(1) gssbpi(2) generic(1) service_nbme(4)
     * }</code>
     */
    public stbtic finbl Oid NT_HOSTBASED_SERVICE
        = Oid.getInstbnce("1.2.840.113554.1.2.1.4");

    /**
     * Nbme type to indicbte b nbmed user on b locbl system.<p>
     * It represents the following Oid vblue:<br>
     *  <code>{ iso(1) member-body(2) United
     * Stbtes(840) mit(113554) infosys(1) gssbpi(2) generic(1) user_nbme(1)
     * }</code>
     */
    public stbtic finbl Oid NT_USER_NAME
        = Oid.getInstbnce("1.2.840.113554.1.2.1.1");

    /**
     * Nbme type to indicbte b numeric user identifier corresponding to b
     * user on b locbl system. (e.g. Uid).<p>
     *
     *  It represents the following Oid vblue:<br>
     * <code>{ iso(1) member-body(2) United Stbtes(840) mit(113554)
     * infosys(1) gssbpi(2) generic(1) mbchine_uid_nbme(2) }</code>
     */
    public stbtic finbl Oid NT_MACHINE_UID_NAME
        = Oid.getInstbnce("1.2.840.113554.1.2.1.2");

    /**
     * Nbme type to indicbte b string of digits representing the numeric
     * user identifier of b user on b locbl system.<p>
     *
     * It represents the following Oid vblue:<br>
     * <code>{ iso(1) member-body(2) United
     * Stbtes(840) mit(113554) infosys(1) gssbpi(2) generic(1)
     * string_uid_nbme(3) }</code>
     */
    public stbtic finbl Oid NT_STRING_UID_NAME
        = Oid.getInstbnce("1.2.840.113554.1.2.1.3");

    /**
     * Nbme type for representing bn bnonymous entity.<p>
     * It represents the following Oid vblue:<br>
     * <code>{ 1(iso), 3(org), 6(dod), 1(internet),
     * 5(security), 6(nbmetypes), 3(gss-bnonymous-nbme) }</code>
     */
    public stbtic finbl Oid NT_ANONYMOUS
        = Oid.getInstbnce("1.3.6.1.5.6.3");

    /**
     * Nbme type used to indicbte bn exported nbme produced by the export
     * method.<p>
     *
     * It represents the following Oid vblue:<br> <code>{ 1(iso),
     * 3(org), 6(dod), 1(internet), 5(security), 6(nbmetypes),
     * 4(gss-bpi-exported-nbme) }</code>
     */
    public stbtic finbl Oid NT_EXPORT_NAME
        = Oid.getInstbnce("1.3.6.1.5.6.4");

    /**
     * Compbres two <code>GSSNbme</code> objects to determine if they refer to the
     * sbme entity.
     *
     * @pbrbm bnother the <code>GSSNbme</code> to compbre this nbme with
     * @return true if the two nbmes contbin bt lebst one primitive element
     * in common. If either of the nbmes represents bn bnonymous entity, the
     * method will return fblse.
     *
     * @throws GSSException when the nbmes cbnnot be compbred, contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public boolebn equbls(GSSNbme bnother) throws GSSException;

    /**
     * Compbres this <code>GSSNbme</code> object to bnother Object thbt might be b
     * <code>GSSNbme</code>. The behbviour is exbctly the sbme bs in {@link
     * #equbls(GSSNbme) equbls} except thbt no GSSException is thrown;
     * instebd, fblse will be returned in the situbtion where bn error
     * occurs.
     * @return true if the object to compbre to is blso b <code>GSSNbme</code> bnd the two
     * nbmes refer to the sbme entity.
     * @pbrbm bnother the object to compbre this nbme to
     * @see #equbls(GSSNbme)
     */
    public boolebn equbls(Object bnother);

    /**
     * Returns b hbshcode vblue for this GSSNbme.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode();

    /**
     * Crebtes b nbme thbt is cbnonicblized for some
     * mechbnism.
     *
     * @return b <code>GSSNbme</code> thbt contbins just one primitive
     * element representing this nbme in b cbnonicblized form for the desired
     * mechbnism.
     * @pbrbm mech the oid for the mechbnism for which the cbnonicbl form of
     * the nbme is requested.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *         {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *         {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSNbme cbnonicblize(Oid mech) throws GSSException;

    /**
     * Returns b cbnonicbl contiguous byte representbtion of b mechbnism nbme
     * (MN), suitbble for direct, byte by byte compbrison by buthorizbtion
     * functions.  If the nbme is not bn MN, implementbtions mby throw b
     * GSSException with the NAME_NOT_MN stbtus code.  If bn implementbtion
     * chooses not to throw bn exception, it should use some system specific
     * defbult mechbnism to cbnonicblize the nbme bnd then export
     * it. Structurblly, bn exported nbme object consists of b hebder
     * contbining bn OID identifying the mechbnism thbt buthenticbted the
     * nbme, bnd b trbiler contbining the nbme itself, where the syntbx of
     * the trbiler is defined by the individubl mechbnism specificbtion. The
     * formbt of the hebder of the output buffer is specified in RFC 2743.<p>
     *
     * The exported nbme is useful when used in lbrge bccess control lists
     * where the overhebd of crebting b <code>GSSNbme</code> object on ebch
     * nbme bnd invoking the equbls method on ebch nbme from the ACL mby be
     * prohibitive.<p>
     *
     * Exported nbmes mby be re-imported by using the byte brrby fbctory
     * method {@link GSSMbnbger#crebteNbme(byte[], Oid)
     * GSSMbnbger.crebteNbme} bnd specifying the NT_EXPORT_NAME bs the nbme
     * type object identifier. The resulting <code>GSSNbme</code> nbme will
     * blso be b MN.<p>
     * @return b byte[] contbining the exported nbme. RFC 2743 defines the
     * "Mechbnism-Independent Exported Nbme Object Formbt" for these bytes.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#BAD_NAME GSSException.BAD_NAME},
     *         {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte[] export() throws GSSException;

    /**
     * Returns b textubl representbtion of the <code>GSSNbme</code> object.  To retrieve
     * the printed nbme formbt, which determines the syntbx of the returned
     * string, use the {@link #getStringNbmeType() getStringNbmeType}
     * method.
     *
     * @return b String representing this nbme in printbble form.
     */
    public String toString();

    /**
     * Returns the nbme type of the printbble
     * representbtion of this nbme thbt cbn be obtbined from the <code>
     * toString</code> method.
     *
     * @return bn Oid representing the nbmespbce of the nbme returned
     * from the toString method.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *         {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public Oid getStringNbmeType() throws GSSException;

    /**
     * Tests if this nbme object represents bn bnonymous entity.
     *
     * @return true if this is bn bnonymous nbme, fblse otherwise.
     */
    public boolebn isAnonymous();

    /**
     * Tests if this nbme object represents b Mechbnism Nbme (MN). An MN is
     * b GSSNbme the contbins exbctly one mechbnism's primitive nbme
     * element.
     *
     * @return true if this is bn MN, fblse otherwise.
     */
    public boolebn isMN();

}
