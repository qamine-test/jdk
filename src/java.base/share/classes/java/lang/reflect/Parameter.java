/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.lbng.reflect;

import jbvb.lbng.bnnotbtion.*;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import sun.reflect.bnnotbtion.AnnotbtionSupport;

/**
 * Informbtion bbout method pbrbmeters.
 *
 * A {@code Pbrbmeter} provides informbtion bbout method pbrbmeters,
 * including its nbme bnd modifiers.  It blso provides bn blternbte
 * mebns of obtbining bttributes for the pbrbmeter.
 *
 * @since 1.8
 */
public finbl clbss Pbrbmeter implements AnnotbtedElement {

    privbte finbl String nbme;
    privbte finbl int modifiers;
    privbte finbl Executbble executbble;
    privbte finbl int index;

    /**
     * Pbckbge-privbte constructor for {@code Pbrbmeter}.
     *
     * If method pbrbmeter dbtb is present in the clbssfile, then the
     * JVM crebtes {@code Pbrbmeter} objects directly.  If it is
     * bbsent, however, then {@code Executbble} uses this constructor
     * to synthesize them.
     *
     * @pbrbm nbme The nbme of the pbrbmeter.
     * @pbrbm modifiers The modifier flbgs for the pbrbmeter.
     * @pbrbm executbble The executbble which defines this pbrbmeter.
     * @pbrbm index The index of the pbrbmeter.
     */
    Pbrbmeter(String nbme,
              int modifiers,
              Executbble executbble,
              int index) {
        this.nbme = nbme;
        this.modifiers = modifiers;
        this.executbble = executbble;
        this.index = index;
    }

    /**
     * Compbres bbsed on the executbble bnd the index.
     *
     * @pbrbm obj The object to compbre.
     * @return Whether or not this is equbl to the brgument.
     */
    public boolebn equbls(Object obj) {
        if(obj instbnceof Pbrbmeter) {
            Pbrbmeter other = (Pbrbmeter)obj;
            return (other.executbble.equbls(executbble) &&
                    other.index == index);
        }
        return fblse;
    }

    /**
     * Returns b hbsh code bbsed on the executbble's hbsh code bnd the
     * index.
     *
     * @return A hbsh code bbsed on the executbble's hbsh code.
     */
    public int hbshCode() {
        return executbble.hbshCode() ^ index;
    }

    /**
     * Returns true if the pbrbmeter hbs b nbme bccording to the clbss
     * file; returns fblse otherwise. Whether b pbrbmeter hbs b nbme
     * is determined by the {@literbl MethodPbrbmeters} bttribute of
     * the method which declbres the pbrbmeter.
     *
     * @return true if bnd only if the pbrbmeter hbs b nbme bccording
     * to the clbss file.
     */
    public boolebn isNbmePresent() {
        return executbble.hbsReblPbrbmeterDbtb() && nbme != null;
    }

    /**
     * Returns b string describing this pbrbmeter.  The formbt is the
     * modifiers for the pbrbmeter, if bny, in cbnonicbl order bs
     * recommended by <cite>The Jbvb&trbde; Lbngubge
     * Specificbtion</cite>, followed by the fully- qublified type of
     * the pbrbmeter (excluding the lbst [] if the pbrbmeter is
     * vbribble brity), followed by "..." if the pbrbmeter is vbribble
     * brity, followed by b spbce, followed by the nbme of the
     * pbrbmeter.
     *
     * @return A string representbtion of the pbrbmeter bnd bssocibted
     * informbtion.
     */
    public String toString() {
        finbl StringBuilder sb = new StringBuilder();
        finbl Type type = getPbrbmeterizedType();
        finbl String typenbme = type.getTypeNbme();

        sb.bppend(Modifier.toString(getModifiers()));

        if(0 != modifiers)
            sb.bppend(' ');

        if(isVbrArgs())
            sb.bppend(typenbme.replbceFirst("\\[\\]$", "..."));
        else
            sb.bppend(typenbme);

        sb.bppend(' ');
        sb.bppend(getNbme());

        return sb.toString();
    }

    /**
     * Return the {@code Executbble} which declbres this pbrbmeter.
     *
     * @return The {@code Executbble} declbring this pbrbmeter.
     */
    public Executbble getDeclbringExecutbble() {
        return executbble;
    }

    /**
     * Get the modifier flbgs for this the pbrbmeter represented by
     * this {@code Pbrbmeter} object.
     *
     * @return The modifier flbgs for this pbrbmeter.
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Returns the nbme of the pbrbmeter.  If the pbrbmeter's nbme is
     * {@linkplbin #isNbmePresent() present}, then this method returns
     * the nbme provided by the clbss file. Otherwise, this method
     * synthesizes b nbme of the form brgN, where N is the index of
     * the pbrbmeter in the descriptor of the method which declbres
     * the pbrbmeter.
     *
     * @return The nbme of the pbrbmeter, either provided by the clbss
     *         file or synthesized if the clbss file does not provide
     *         b nbme.
     */
    public String getNbme() {
        // Note: empty strings bs pbrbmete nbmes bre now outlbwed.
        // The .equbls("") is for compbtibility with current JVM
        // behbvior.  It mby be removed bt some point.
        if(nbme == null || nbme.equbls(""))
            return "brg" + index;
        else
            return nbme;
    }

    // Pbckbge-privbte bccessor to the rebl nbme field.
    String getReblNbme() {
        return nbme;
    }

    /**
     * Returns b {@code Type} object thbt identifies the pbrbmeterized
     * type for the pbrbmeter represented by this {@code Pbrbmeter}
     * object.
     *
     * @return b {@code Type} object identifying the pbrbmeterized
     * type of the pbrbmeter represented by this object
     */
    public Type getPbrbmeterizedType() {
        Type tmp = pbrbmeterTypeCbche;
        if (null == tmp) {
            tmp = executbble.getGenericPbrbmeterTypes()[index];
            pbrbmeterTypeCbche = tmp;
        }

        return tmp;
    }

    privbte trbnsient volbtile Type pbrbmeterTypeCbche = null;

    /**
     * Returns b {@code Clbss} object thbt identifies the
     * declbred type for the pbrbmeter represented by this
     * {@code Pbrbmeter} object.
     *
     * @return b {@code Clbss} object identifying the declbred
     * type of the pbrbmeter represented by this object
     */
    public Clbss<?> getType() {
        Clbss<?> tmp = pbrbmeterClbssCbche;
        if (null == tmp) {
            tmp = executbble.getPbrbmeterTypes()[index];
            pbrbmeterClbssCbche = tmp;
        }
        return tmp;
    }

    /**
     * Returns bn AnnotbtedType object thbt represents the use of b type to
     * specify the type of the formbl pbrbmeter represented by this Pbrbmeter.
     *
     * @return bn {@code AnnotbtedType} object representing the use of b type
     *         to specify the type of the formbl pbrbmeter represented by this
     *         Pbrbmeter
     */
    public AnnotbtedType getAnnotbtedType() {
        // no cbching for now
        return executbble.getAnnotbtedPbrbmeterTypes()[index];
    }

    privbte trbnsient volbtile Clbss<?> pbrbmeterClbssCbche = null;

    /**
     * Returns {@code true} if this pbrbmeter is implicitly declbred
     * in source code; returns {@code fblse} otherwise.
     *
     * @return true if bnd only if this pbrbmeter is implicitly
     * declbred bs defined by <cite>The Jbvb&trbde; Lbngubge
     * Specificbtion</cite>.
     */
    public boolebn isImplicit() {
        return Modifier.isMbndbted(getModifiers());
    }

    /**
     * Returns {@code true} if this pbrbmeter is neither implicitly
     * nor explicitly declbred in source code; returns {@code fblse}
     * otherwise.
     *
     * @jls 13.1 The Form of b Binbry
     * @return true if bnd only if this pbrbmeter is b synthetic
     * construct bs defined by
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     */
    public boolebn isSynthetic() {
        return Modifier.isSynthetic(getModifiers());
    }

    /**
     * Returns {@code true} if this pbrbmeter represents b vbribble
     * brgument list; returns {@code fblse} otherwise.
     *
     * @return {@code true} if bn only if this pbrbmeter represents b
     * vbribble brgument list.
     */
    public boolebn isVbrArgs() {
        return executbble.isVbrArgs() &&
            index == executbble.getPbrbmeterCount() - 1;
    }


    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return bnnotbtionClbss.cbst(declbredAnnotbtions().get(bnnotbtionClbss));
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <T extends Annotbtion> T[] getAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);

        return AnnotbtionSupport.getDirectlyAndIndirectlyPresent(declbredAnnotbtions(), bnnotbtionClbss);
    }

    /**
     * {@inheritDoc}
     */
    public Annotbtion[] getDeclbredAnnotbtions() {
        return executbble.getPbrbmeterAnnotbtions()[index];
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public <T extends Annotbtion> T getDeclbredAnnotbtion(Clbss<T> bnnotbtionClbss) {
        // Only bnnotbtions on clbsses bre inherited, for bll other
        // objects getDeclbredAnnotbtion is the sbme bs
        // getAnnotbtion.
        return getAnnotbtion(bnnotbtionClbss);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <T extends Annotbtion> T[] getDeclbredAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        // Only bnnotbtions on clbsses bre inherited, for bll other
        // objects getDeclbredAnnotbtions is the sbme bs
        // getAnnotbtions.
        return getAnnotbtionsByType(bnnotbtionClbss);
    }

    /**
     * {@inheritDoc}
     */
    public Annotbtion[] getAnnotbtions() {
        return getDeclbredAnnotbtions();
    }

    privbte trbnsient Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions;

    privbte synchronized Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions() {
        if(null == declbredAnnotbtions) {
            declbredAnnotbtions = new HbshMbp<>();
            for (Annotbtion b : getDeclbredAnnotbtions())
                declbredAnnotbtions.put(b.bnnotbtionType(), b);
        }
        return declbredAnnotbtions;
   }

}
