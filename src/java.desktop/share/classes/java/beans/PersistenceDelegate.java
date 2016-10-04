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
pbckbge jbvb.bebns;

/**
 * The PersistenceDelegbte clbss tbkes the responsibility
 * for expressing the stbte of bn instbnce of b given clbss
 * in terms of the methods in the clbss's public API. Instebd
 * of bssocibting the responsibility of persistence with
 * the clbss itself bs is done, for exbmple, by the
 * <code>rebdObject</code> bnd <code>writeObject</code>
 * methods used by the <code>ObjectOutputStrebm</code>, strebms like
 * the <code>XMLEncoder</code> which
 * use this delegbtion model cbn hbve their behbvior controlled
 * independently of the clbsses themselves. Normblly, the clbss
 * is the best plbce to put such informbtion bnd conventions
 * cbn ebsily be expressed in this delegbtion scheme to do just thbt.
 * Sometimes however, it is the cbse thbt b minor problem
 * in b single clbss prevents bn entire object grbph from
 * being written bnd this cbn lebve the bpplicbtion
 * developer with no recourse but to bttempt to shbdow
 * the problembtic clbsses locblly or use blternbtive
 * persistence techniques. In situbtions like these, the
 * delegbtion model gives b relbtively clebn mechbnism for
 * the bpplicbtion developer to intervene in bll pbrts of the
 * seriblizbtion process without requiring thbt modificbtions
 * be mbde to the implementbtion of clbsses which bre not pbrt
 * of the bpplicbtion itself.
 * <p>
 * In bddition to using b delegbtion model, this persistence
 * scheme differs from trbditionbl seriblizbtion schemes
 * in requiring bn bnblog of the <code>writeObject</code>
 * method without b corresponding <code>rebdObject</code>
 * method. The <code>writeObject</code> bnblog encodes ebch
 * instbnce in terms of its public API bnd there is no need to
 * define b <code>rebdObject</code> bnblog
 * since the procedure for rebding the seriblized form
 * is defined by the sembntics of method invocbtion bs lbid
 * out in the Jbvb Lbngubge Specificbtion.
 * Brebking the dependency between <code>writeObject</code>
 * bnd <code>rebdObject</code> implementbtions, which mby
 * chbnge from version to version, is the key fbctor
 * in mbking the brchives produced by this technique immune
 * to chbnges in the privbte implementbtions of the clbsses
 * to which they refer.
 * <p>
 * A persistence delegbte, mby tbke control of bll
 * bspects of the persistence of bn object including:
 * <ul>
 * <li>
 * Deciding whether or not bn instbnce cbn be mutbted
 * into bnother instbnce of the sbme clbss.
 * <li>
 * Instbntibting the object, either by cblling b
 * public constructor or b public fbctory method.
 * <li>
 * Performing the initiblizbtion of the object.
 * </ul>
 * @see XMLEncoder
 *
 * @since 1.4
 *
 * @buthor Philip Milne
 */

public bbstrbct clbss PersistenceDelegbte {

    /**
     * The <code>writeObject</code> is b single entry point to the persistence
     * bnd is used by b <code>Encoder</code> in the trbditionbl
     * mode of delegbtion. Although this method is not finbl,
     * it should not need to be subclbssed under normbl circumstbnces.
     * <p>
     * This implementbtion first checks to see if the strebm
     * hbs blrebdy encountered this object. Next the
     * <code>mutbtesTo</code> method is cblled to see if
     * thbt cbndidbte returned from the strebm cbn
     * be mutbted into bn bccurbte copy of <code>oldInstbnce</code>.
     * If it cbn, the <code>initiblize</code> method is cblled to
     * perform the initiblizbtion. If not, the cbndidbte is removed
     * from the strebm, bnd the <code>instbntibte</code> method
     * is cblled to crebte b new cbndidbte for this object.
     *
     * @pbrbm oldInstbnce The instbnce thbt will be crebted by this expression.
     * @pbrbm out The strebm to which this expression will be written.
     *
     * @throws NullPointerException if {@code out} is {@code null}
     */
    public void writeObject(Object oldInstbnce, Encoder out) {
        Object newInstbnce = out.get(oldInstbnce);
        if (!mutbtesTo(oldInstbnce, newInstbnce)) {
            out.remove(oldInstbnce);
            out.writeExpression(instbntibte(oldInstbnce, out));
        }
        else {
            initiblize(oldInstbnce.getClbss(), oldInstbnce, newInstbnce, out);
        }
    }

    /**
     * Returns true if bn <em>equivblent</em> copy of <code>oldInstbnce</code> mby be
     * crebted by bpplying b series of stbtements to <code>newInstbnce</code>.
     * In the specificbtion of this method, we mebn by equivblent thbt the modified instbnce
     * is indistinguishbble from <code>oldInstbnce</code> in the behbvior
     * of the relevbnt methods in its public API. [Note: we use the
     * phrbse <em>relevbnt</em> methods rbther thbn <em>bll</em> methods
     * here only becbuse, to be strictly correct, methods like <code>hbshCode</code>
     * bnd <code>toString</code> prevent most clbsses from producing truly
     * indistinguishbble copies of their instbnces].
     * <p>
     * The defbult behbvior returns <code>true</code>
     * if the clbsses of the two instbnces bre the sbme.
     *
     * @pbrbm oldInstbnce The instbnce to be copied.
     * @pbrbm newInstbnce The instbnce thbt is to be modified.
     * @return True if bn equivblent copy of <code>newInstbnce</code> mby be
     *         crebted by bpplying b series of mutbtions to <code>oldInstbnce</code>.
     */
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return (newInstbnce != null && oldInstbnce != null &&
                oldInstbnce.getClbss() == newInstbnce.getClbss());
    }

    /**
     * Returns bn expression whose vblue is <code>oldInstbnce</code>.
     * This method is used to chbrbcterize the constructor
     * or fbctory method thbt should be used to crebte the given object.
     * For exbmple, the <code>instbntibte</code> method of the persistence
     * delegbte for the <code>Field</code> clbss could be defined bs follows:
     * <pre>
     * Field f = (Field)oldInstbnce;
     * return new Expression(f, f.getDeclbringClbss(), "getField", new Object[]{f.getNbme()});
     * </pre>
     * Note thbt we declbre the vblue of the returned expression so thbt
     * the vblue of the expression (bs returned by <code>getVblue</code>)
     * will be identicbl to <code>oldInstbnce</code>.
     *
     * @pbrbm oldInstbnce The instbnce thbt will be crebted by this expression.
     * @pbrbm out The strebm to which this expression will be written.
     * @return An expression whose vblue is <code>oldInstbnce</code>.
     *
     * @throws NullPointerException if {@code out} is {@code null}
     *                              bnd this vblue is used in the method
     */
    protected bbstrbct Expression instbntibte(Object oldInstbnce, Encoder out);

    /**
     * Produce b series of stbtements with side effects on <code>newInstbnce</code>
     * so thbt the new instbnce becomes <em>equivblent</em> to <code>oldInstbnce</code>.
     * In the specificbtion of this method, we mebn by equivblent thbt, bfter the method
     * returns, the modified instbnce is indistinguishbble from
     * <code>newInstbnce</code> in the behbvior of bll methods in its
     * public API.
     * <p>
     * The implementbtion typicblly bchieves this gobl by producing b series of
     * "whbt hbppened" stbtements involving the <code>oldInstbnce</code>
     * bnd its publicly bvbilbble stbte. These stbtements bre sent
     * to the output strebm using its <code>writeExpression</code>
     * method which returns bn expression involving elements in
     * b cloned environment simulbting the stbte of bn input strebm during
     * rebding. Ebch stbtement returned will hbve hbd bll instbnces
     * the old environment replbced with objects which exist in the new
     * one. In pbrticulbr, references to the tbrget of these stbtements,
     * which stbrt out bs references to <code>oldInstbnce</code> bre returned
     * bs references to the <code>newInstbnce</code> instebd.
     * Executing these stbtements effects bn incrementbl
     * blignment of the stbte of the two objects bs b series of
     * modificbtions to the objects in the new environment.
     * By the time the initiblize method returns it should be impossible
     * to tell the two instbnces bpbrt by using their public APIs.
     * Most importbntly, the sequence of steps thbt were used to mbke
     * these objects bppebr equivblent will hbve been recorded
     * by the output strebm bnd will form the bctubl output when
     * the strebm is flushed.
     * <p>
     * The defbult implementbtion, cblls the <code>initiblize</code>
     * method of the type's superclbss.
     *
     * @pbrbm type the type of the instbnces
     * @pbrbm oldInstbnce The instbnce to be copied.
     * @pbrbm newInstbnce The instbnce thbt is to be modified.
     * @pbrbm out The strebm to which bny initiblizbtion stbtements should be written.
     *
     * @throws NullPointerException if {@code out} is {@code null}
     */
    protected void initiblize(Clbss<?> type,
                              Object oldInstbnce, Object newInstbnce,
                              Encoder out)
    {
        Clbss<?> superType = type.getSuperclbss();
        PersistenceDelegbte info = out.getPersistenceDelegbte(superType);
        info.initiblize(superType, oldInstbnce, newInstbnce, out);
    }
}
