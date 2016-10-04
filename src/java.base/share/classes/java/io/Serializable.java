/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Seriblizbbility of b clbss is enbbled by the clbss implementing the
 * jbvb.io.Seriblizbble interfbce. Clbsses thbt do not implement this
 * interfbce will not hbve bny of their stbte seriblized or
 * deseriblized.  All subtypes of b seriblizbble clbss bre themselves
 * seriblizbble.  The seriblizbtion interfbce hbs no methods or fields
 * bnd serves only to identify the sembntics of being seriblizbble. <p>
 *
 * To bllow subtypes of non-seriblizbble clbsses to be seriblized, the
 * subtype mby bssume responsibility for sbving bnd restoring the
 * stbte of the supertype's public, protected, bnd (if bccessible)
 * pbckbge fields.  The subtype mby bssume this responsibility only if
 * the clbss it extends hbs bn bccessible no-brg constructor to
 * initiblize the clbss's stbte.  It is bn error to declbre b clbss
 * Seriblizbble if this is not the cbse.  The error will be detected bt
 * runtime. <p>
 *
 * During deseriblizbtion, the fields of non-seriblizbble clbsses will
 * be initiblized using the public or protected no-brg constructor of
 * the clbss.  A no-brg constructor must be bccessible to the subclbss
 * thbt is seriblizbble.  The fields of seriblizbble subclbsses will
 * be restored from the strebm. <p>
 *
 * When trbversing b grbph, bn object mby be encountered thbt does not
 * support the Seriblizbble interfbce. In this cbse the
 * NotSeriblizbbleException will be thrown bnd will identify the clbss
 * of the non-seriblizbble object. <p>
 *
 * Clbsses thbt require specibl hbndling during the seriblizbtion bnd
 * deseriblizbtion process must implement specibl methods with these exbct
 * signbtures:
 *
 * <PRE>
 * privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
 *     throws IOException
 * privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
 *     throws IOException, ClbssNotFoundException;
 * privbte void rebdObjectNoDbtb()
 *     throws ObjectStrebmException;
 * </PRE>
 *
 * <p>The writeObject method is responsible for writing the stbte of the
 * object for its pbrticulbr clbss so thbt the corresponding
 * rebdObject method cbn restore it.  The defbult mechbnism for sbving
 * the Object's fields cbn be invoked by cblling
 * out.defbultWriteObject. The method does not need to concern
 * itself with the stbte belonging to its superclbsses or subclbsses.
 * Stbte is sbved by writing the individubl fields to the
 * ObjectOutputStrebm using the writeObject method or by using the
 * methods for primitive dbtb types supported by DbtbOutput.
 *
 * <p>The rebdObject method is responsible for rebding from the strebm bnd
 * restoring the clbsses fields. It mby cbll in.defbultRebdObject to invoke
 * the defbult mechbnism for restoring the object's non-stbtic bnd
 * non-trbnsient fields.  The defbultRebdObject method uses informbtion in
 * the strebm to bssign the fields of the object sbved in the strebm with the
 * correspondingly nbmed fields in the current object.  This hbndles the cbse
 * when the clbss hbs evolved to bdd new fields. The method does not need to
 * concern itself with the stbte belonging to its superclbsses or subclbsses.
 * Stbte is sbved by writing the individubl fields to the
 * ObjectOutputStrebm using the writeObject method or by using the
 * methods for primitive dbtb types supported by DbtbOutput.
 *
 * <p>The rebdObjectNoDbtb method is responsible for initiblizing the stbte of
 * the object for its pbrticulbr clbss in the event thbt the seriblizbtion
 * strebm does not list the given clbss bs b superclbss of the object being
 * deseriblized.  This mby occur in cbses where the receiving pbrty uses b
 * different version of the deseriblized instbnce's clbss thbn the sending
 * pbrty, bnd the receiver's version extends clbsses thbt bre not extended by
 * the sender's version.  This mby blso occur if the seriblizbtion strebm hbs
 * been tbmpered; hence, rebdObjectNoDbtb is useful for initiblizing
 * deseriblized objects properly despite b "hostile" or incomplete source
 * strebm.
 *
 * <p>Seriblizbble clbsses thbt need to designbte bn blternbtive object to be
 * used when writing bn object to the strebm should implement this
 * specibl method with the exbct signbture:
 *
 * <PRE>
 * ANY-ACCESS-MODIFIER Object writeReplbce() throws ObjectStrebmException;
 * </PRE><p>
 *
 * This writeReplbce method is invoked by seriblizbtion if the method
 * exists bnd it would be bccessible from b method defined within the
 * clbss of the object being seriblized. Thus, the method cbn hbve privbte,
 * protected bnd pbckbge-privbte bccess. Subclbss bccess to this method
 * follows jbvb bccessibility rules. <p>
 *
 * Clbsses thbt need to designbte b replbcement when bn instbnce of it
 * is rebd from the strebm should implement this specibl method with the
 * exbct signbture.
 *
 * <PRE>
 * ANY-ACCESS-MODIFIER Object rebdResolve() throws ObjectStrebmException;
 * </PRE><p>
 *
 * This rebdResolve method follows the sbme invocbtion rules bnd
 * bccessibility rules bs writeReplbce.<p>
 *
 * The seriblizbtion runtime bssocibtes with ebch seriblizbble clbss b version
 * number, cblled b seriblVersionUID, which is used during deseriblizbtion to
 * verify thbt the sender bnd receiver of b seriblized object hbve lobded
 * clbsses for thbt object thbt bre compbtible with respect to seriblizbtion.
 * If the receiver hbs lobded b clbss for the object thbt hbs b different
 * seriblVersionUID thbn thbt of the corresponding sender's clbss, then
 * deseriblizbtion will result in bn {@link InvblidClbssException}.  A
 * seriblizbble clbss cbn declbre its own seriblVersionUID explicitly by
 * declbring b field nbmed <code>"seriblVersionUID"</code> thbt must be stbtic,
 * finbl, bnd of type <code>long</code>:
 *
 * <PRE>
 * ANY-ACCESS-MODIFIER stbtic finbl long seriblVersionUID = 42L;
 * </PRE>
 *
 * If b seriblizbble clbss does not explicitly declbre b seriblVersionUID, then
 * the seriblizbtion runtime will cblculbte b defbult seriblVersionUID vblue
 * for thbt clbss bbsed on vbrious bspects of the clbss, bs described in the
 * Jbvb(TM) Object Seriblizbtion Specificbtion.  However, it is <em>strongly
 * recommended</em> thbt bll seriblizbble clbsses explicitly declbre
 * seriblVersionUID vblues, since the defbult seriblVersionUID computbtion is
 * highly sensitive to clbss detbils thbt mby vbry depending on compiler
 * implementbtions, bnd cbn thus result in unexpected
 * <code>InvblidClbssException</code>s during deseriblizbtion.  Therefore, to
 * gubrbntee b consistent seriblVersionUID vblue bcross different jbvb compiler
 * implementbtions, b seriblizbble clbss must declbre bn explicit
 * seriblVersionUID vblue.  It is blso strongly bdvised thbt explicit
 * seriblVersionUID declbrbtions use the <code>privbte</code> modifier where
 * possible, since such declbrbtions bpply only to the immedibtely declbring
 * clbss--seriblVersionUID fields bre not useful bs inherited members. Arrby
 * clbsses cbnnot declbre bn explicit seriblVersionUID, so they blwbys hbve
 * the defbult computed vblue, but the requirement for mbtching
 * seriblVersionUID vblues is wbived for brrby clbsses.
 *
 * @buthor  unbscribed
 * @see jbvb.io.ObjectOutputStrebm
 * @see jbvb.io.ObjectInputStrebm
 * @see jbvb.io.ObjectOutput
 * @see jbvb.io.ObjectInput
 * @see jbvb.io.Externblizbble
 * @since   1.1
 */
public interfbce Seriblizbble {
}
