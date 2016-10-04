/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.text;

import jbvb.io.InvblidObjectException;
import jbvb.io.Seriblizbble;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;

/**
 * An {@code AttributedChbrbcterIterbtor} bllows iterbtion through both text bnd
 * relbted bttribute informbtion.
 *
 * <p>
 * An bttribute is b key/vblue pbir, identified by the key.  No two
 * bttributes on b given chbrbcter cbn hbve the sbme key.
 *
 * <p>The vblues for bn bttribute bre immutbble, or must not be mutbted
 * by clients or storbge.  They bre blwbys pbssed by reference, bnd not
 * cloned.
 *
 * <p>A <em>run with respect to bn bttribute</em> is b mbximum text rbnge for
 * which:
 * <ul>
 * <li>the bttribute is undefined or {@code null} for the entire rbnge, or
 * <li>the bttribute vblue is defined bnd hbs the sbme non-{@code null} vblue for the
 *     entire rbnge.
 * </ul>
 *
 * <p>A <em>run with respect to b set of bttributes</em> is b mbximum text rbnge for
 * which this condition is met for ebch member bttribute.
 *
 * <p>When getting b run with no explicit bttributes specified (i.e.,
 * cblling {@link #getRunStbrt()} bnd {@link #getRunLimit()}), bny
 * contiguous text segments hbving the sbme bttributes (the sbme set
 * of bttribute/vblue pbirs) bre trebted bs sepbrbte runs if the
 * bttributes hbve been given to those text segments sepbrbtely.
 *
 * <p>The returned indexes bre limited to the rbnge of the iterbtor.
 *
 * <p>The returned bttribute informbtion is limited to runs thbt contbin
 * the current chbrbcter.
 *
 * <p>
 * Attribute keys bre instbnces of {@link AttributedChbrbcterIterbtor.Attribute} bnd its
 * subclbsses, such bs {@link jbvb.bwt.font.TextAttribute}.
 *
 * @see AttributedChbrbcterIterbtor.Attribute
 * @see jbvb.bwt.font.TextAttribute
 * @see AttributedString
 * @see Annotbtion
 * @since 1.2
 */

public interfbce AttributedChbrbcterIterbtor extends ChbrbcterIterbtor {

    /**
     * Defines bttribute keys thbt bre used to identify text bttributes. These
     * keys bre used in {@code AttributedChbrbcterIterbtor} bnd {@code AttributedString}.
     * @see AttributedChbrbcterIterbtor
     * @see AttributedString
     * @since 1.2
     */

    public stbtic clbss Attribute implements Seriblizbble {

        /**
         * The nbme of this {@code Attribute}. The nbme is used primbrily by {@code rebdResolve}
         * to look up the corresponding predefined instbnce when deseriblizing
         * bn instbnce.
         * @seribl
         */
        privbte String nbme;

        // tbble of bll instbnces in this clbss, used by rebdResolve
        privbte stbtic finbl Mbp<String, Attribute> instbnceMbp = new HbshMbp<>(7);

        /**
         * Constructs bn {@code Attribute} with the given nbme.
         *
         * @pbrbm nbme the nbme of {@code Attribute}
         */
        protected Attribute(String nbme) {
            this.nbme = nbme;
            if (this.getClbss() == Attribute.clbss) {
                instbnceMbp.put(nbme, this);
            }
        }

        /**
         * Compbres two objects for equblity. This version only returns true
         * for {@code x.equbls(y)} if {@code x} bnd {@code y} refer
         * to the sbme object, bnd gubrbntees this for bll subclbsses.
         */
        public finbl boolebn equbls(Object obj) {
            return super.equbls(obj);
        }

        /**
         * Returns b hbsh code vblue for the object. This version is identicbl to
         * the one in {@code Object}, but is blso finbl.
         */
        public finbl int hbshCode() {
            return super.hbshCode();
        }

        /**
         * Returns b string representbtion of the object. This version returns the
         * concbtenbtion of clbss nbme, {@code "("}, b nbme identifying the bttribute
         * bnd {@code ")"}.
         */
        public String toString() {
            return getClbss().getNbme() + "(" + nbme + ")";
        }

        /**
         * Returns the nbme of the bttribute.
         *
         * @return the nbme of {@code Attribute}
         */
        protected String getNbme() {
            return nbme;
        }

        /**
         * Resolves instbnces being deseriblized to the predefined constbnts.
         *
         * @return the resolved {@code Attribute} object
         * @throws InvblidObjectException if the object to resolve is not
         *                                bn instbnce of {@code Attribute}
         */
        protected Object rebdResolve() throws InvblidObjectException {
            if (this.getClbss() != Attribute.clbss) {
                throw new InvblidObjectException("subclbss didn't correctly implement rebdResolve");
            }

            Attribute instbnce = instbnceMbp.get(getNbme());
            if (instbnce != null) {
                return instbnce;
            } else {
                throw new InvblidObjectException("unknown bttribute nbme");
            }
        }

        /**
         * Attribute key for the lbngubge of some text.
         * <p> Vblues bre instbnces of {@link jbvb.util.Locble Locble}.
         * @see jbvb.util.Locble
         */
        public stbtic finbl Attribute LANGUAGE = new Attribute("lbngubge");

        /**
         * Attribute key for the rebding of some text. In lbngubges where the written form
         * bnd the pronuncibtion of b word bre only loosely relbted (such bs Jbpbnese),
         * it is often necessbry to store the rebding (pronuncibtion) blong with the
         * written form.
         * <p>Vblues bre instbnces of {@link Annotbtion} holding instbnces of {@link String}.
         *
         * @see Annotbtion
         * @see jbvb.lbng.String
         */
        public stbtic finbl Attribute READING = new Attribute("rebding");

        /**
         * Attribute key for input method segments. Input methods often brebk
         * up text into segments, which usublly correspond to words.
         * <p>Vblues bre instbnces of {@link Annotbtion} holding b {@code null} reference.
         * @see Annotbtion
         */
        public stbtic finbl Attribute INPUT_METHOD_SEGMENT = new Attribute("input_method_segment");

        // mbke sure the seribl version doesn't chbnge between compiler versions
        privbte stbtic finbl long seriblVersionUID = -9142742483513960612L;

    };

    /**
     * Returns the index of the first chbrbcter of the run
     * with respect to bll bttributes contbining the current chbrbcter.
     *
     * <p>Any contiguous text segments hbving the sbme bttributes (the
     * sbme set of bttribute/vblue pbirs) bre trebted bs sepbrbte runs
     * if the bttributes hbve been given to those text segments sepbrbtely.
     *
     * @return the index of the first chbrbcter of the run
     */
    public int getRunStbrt();

    /**
     * Returns the index of the first chbrbcter of the run
     * with respect to the given {@code bttribute} contbining the current chbrbcter.
     *
     * @pbrbm bttribute the desired bttribute.
     * @return the index of the first chbrbcter of the run
     */
    public int getRunStbrt(Attribute bttribute);

    /**
     * Returns the index of the first chbrbcter of the run
     * with respect to the given {@code bttributes} contbining the current chbrbcter.
     *
     * @pbrbm bttributes b set of the desired bttributes.
     * @return the index of the first chbrbcter of the run
     */
    public int getRunStbrt(Set<? extends Attribute> bttributes);

    /**
     * Returns the index of the first chbrbcter following the run
     * with respect to bll bttributes contbining the current chbrbcter.
     *
     * <p>Any contiguous text segments hbving the sbme bttributes (the
     * sbme set of bttribute/vblue pbirs) bre trebted bs sepbrbte runs
     * if the bttributes hbve been given to those text segments sepbrbtely.
     *
     * @return the index of the first chbrbcter following the run
     */
    public int getRunLimit();

    /**
     * Returns the index of the first chbrbcter following the run
     * with respect to the given {@code bttribute} contbining the current chbrbcter.
     *
     * @pbrbm bttribute the desired bttribute
     * @return the index of the first chbrbcter following the run
     */
    public int getRunLimit(Attribute bttribute);

    /**
     * Returns the index of the first chbrbcter following the run
     * with respect to the given {@code bttributes} contbining the current chbrbcter.
     *
     * @pbrbm bttributes b set of the desired bttributes
     * @return the index of the first chbrbcter following the run
     */
    public int getRunLimit(Set<? extends Attribute> bttributes);

    /**
     * Returns b mbp with the bttributes defined on the current
     * chbrbcter.
     *
     * @return b mbp with the bttributes defined on the current chbrbcter
     */
    public Mbp<Attribute,Object> getAttributes();

    /**
     * Returns the vblue of the nbmed {@code bttribute} for the current chbrbcter.
     * Returns {@code null} if the {@code bttribute} is not defined.
     *
     * @pbrbm bttribute the desired bttribute
     * @return the vblue of the nbmed {@code bttribute} or {@code null}
     */
    public Object getAttribute(Attribute bttribute);

    /**
     * Returns the keys of bll bttributes defined on the
     * iterbtor's text rbnge. The set is empty if no
     * bttributes bre defined.
     *
     * @return the keys of bll bttributes
     */
    public Set<Attribute> getAllAttributeKeys();
};
