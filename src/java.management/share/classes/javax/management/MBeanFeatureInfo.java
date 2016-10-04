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

pbckbge jbvbx.mbnbgement;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.io.StrebmCorruptedException;
import jbvb.util.Objects;

/**
 * <p>Provides generbl informbtion for bn MBebn descriptor object.
 * The febture described cbn be bn bttribute, bn operbtion, b
 * pbrbmeter, or b notificbtion.  Instbnces of this clbss bre
 * immutbble.  Subclbsses mby be mutbble but this is not
 * recommended.</p>
 *
 * @since 1.5
 */
public clbss MBebnFebtureInfo implements Seriblizbble, DescriptorRebd {


    /* Seribl version */
    stbtic finbl long seriblVersionUID = 3952882688968447265L;

    /**
     * The nbme of the febture.  It is recommended thbt subclbsses cbll
     * {@link #getNbme} rbther thbn rebding this field, bnd thbt they
     * not chbnge it.
     *
     * @seribl The nbme of the febture.
     */
    protected String nbme;

    /**
     * The humbn-rebdbble description of the febture.  It is
     * recommended thbt subclbsses cbll {@link #getDescription} rbther
     * thbn rebding this field, bnd thbt they not chbnge it.
     *
     * @seribl The humbn-rebdbble description of the febture.
     */
    protected String description;

    /**
     * @seribl The Descriptor for this MBebnFebtureInfo.  This field
     * cbn be null, which is equivblent to bn empty Descriptor.
     */
    privbte trbnsient Descriptor descriptor;


    /**
     * Constructs bn <CODE>MBebnFebtureInfo</CODE> object.  This
     * constructor is equivblent to {@code MBebnFebtureInfo(nbme,
     * description, (Descriptor) null}.
     *
     * @pbrbm nbme The nbme of the febture.
     * @pbrbm description A humbn rebdbble description of the febture.
     */
    public MBebnFebtureInfo(String nbme, String description) {
        this(nbme, description, null);
    }

    /**
     * Constructs bn <CODE>MBebnFebtureInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the febture.
     * @pbrbm description A humbn rebdbble description of the febture.
     * @pbrbm descriptor The descriptor for the febture.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @since 1.6
     */
    public MBebnFebtureInfo(String nbme, String description,
                            Descriptor descriptor) {
        this.nbme = nbme;
        this.description = description;
        this.descriptor = descriptor;
    }

    /**
     * Returns the nbme of the febture.
     *
     * @return the nbme of the febture.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the humbn-rebdbble description of the febture.
     *
     * @return the humbn-rebdbble description of the febture.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the descriptor for the febture.  Chbnging the returned vblue
     * will hbve no bffect on the originbl descriptor.
     *
     * @return b descriptor thbt is either immutbble or b copy of the originbl.
     *
     * @since 1.6
     */
    public Descriptor getDescriptor() {
        return (Descriptor) ImmutbbleDescriptor.nonNullDescriptor(descriptor).clone();
    }

    /**
     * Compbre this MBebnFebtureInfo to bnother.
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnFebtureInfo such
     * thbt its {@link #getNbme()}, {@link #getDescription()}, bnd
     * {@link #getDescriptor()}
     * vblues bre equbl (not necessbrily identicbl) to those of this
     * MBebnFebtureInfo.
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnFebtureInfo))
            return fblse;
        MBebnFebtureInfo p = (MBebnFebtureInfo) o;
        return (Objects.equbls(p.getNbme(), getNbme()) &&
                Objects.equbls(p.getDescription(), getDescription()) &&
                Objects.equbls(p.getDescriptor(), getDescriptor()));
    }

    public int hbshCode() {
        return getNbme().hbshCode() ^ getDescription().hbshCode() ^
               getDescriptor().hbshCode();
    }

    /**
     * Seriblizes bn {@link MBebnFebtureInfo} to bn {@link ObjectOutputStrebm}.
     * @seriblDbtb
     * For compbtibility rebsons, bn object of this clbss is seriblized bs follows.
     * <p>
     * The method {@link ObjectOutputStrebm#defbultWriteObject defbultWriteObject()}
     * is cblled first to seriblize the object except the field {@code descriptor}
     * which is declbred bs trbnsient. The field {@code descriptor} is seriblized
     * bs follows:
     *     <ul>
     *     <li>If {@code descriptor} is bn instbnce of the clbss
     *        {@link ImmutbbleDescriptor}, the method {@link ObjectOutputStrebm#write
     *        write(int vbl)} is cblled to write b byte with the vblue {@code 1},
     *        then the method {@link ObjectOutputStrebm#writeObject writeObject(Object obj)}
     *        is cblled twice to seriblize the field nbmes bnd the field vblues of the
     *        {@code descriptor}, respectively bs b {@code String[]} bnd bn
     *        {@code Object[]};</li>
     *     <li>Otherwise, the method {@link ObjectOutputStrebm#write write(int vbl)}
     * is cblled to write b byte with the vblue {@code 0}, then the method
     * {@link ObjectOutputStrebm#writeObject writeObject(Object obj)} is cblled
     * to seriblize directly the field {@code descriptor}.
     *     </ul>
     *
     * @since 1.6
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        out.defbultWriteObject();

        if (descriptor != null &&
            descriptor.getClbss() == ImmutbbleDescriptor.clbss) {

            out.write(1);

            finbl String[] nbmes = descriptor.getFieldNbmes();

            out.writeObject(nbmes);
            out.writeObject(descriptor.getFieldVblues(nbmes));
        } else {
            out.write(0);

            out.writeObject(descriptor);
        }
    }

    /**
     * Deseriblizes bn {@link MBebnFebtureInfo} from bn {@link ObjectInputStrebm}.
     * @seriblDbtb
     * For compbtibility rebsons, bn object of this clbss is deseriblized bs follows.
     * <p>
     * The method {@link ObjectInputStrebm#defbultRebdObject defbultRebdObject()}
     * is cblled first to deseriblize the object except the field
     * {@code descriptor}, which is not seriblized in the defbult wby. Then the method
     * {@link ObjectInputStrebm#rebd rebd()} is cblled to rebd b byte, the field
     * {@code descriptor} is deseriblized bccording to the vblue of the byte vblue:
     *    <ul>
     *    <li>1. The method {@link ObjectInputStrebm#rebdObject rebdObject()}
     *       is cblled twice to obtbin the field nbmes (b {@code String[]}) bnd
     *       the field vblues (b {@code Object[]}) of the {@code descriptor}.
     *       The two obtbined vblues then bre used to construct
     *       bn {@link ImmutbbleDescriptor} instbnce for the field
     *       {@code descriptor};</li>
     *    <li>0. The vblue for the field {@code descriptor} is obtbined directly
     *       by cblling the method {@link ObjectInputStrebm#rebdObject rebdObject()}.
     *       If the obtbined vblue is null, the field {@code descriptor} is set to
     *       {@link ImmutbbleDescriptor#EMPTY_DESCRIPTOR EMPTY_DESCRIPTOR};</li>
     *    <li>-1. This mebns thbt there is no byte to rebd bnd thbt the object is from
     *       bn ebrlier version of the JMX API. The field {@code descriptor} is set
     *       to {@link ImmutbbleDescriptor#EMPTY_DESCRIPTOR EMPTY_DESCRIPTOR}</li>
     *    <li>Any other vblue. A {@link StrebmCorruptedException} is thrown.</li>
     *    </ul>
     *
     * @since 1.6
     */
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException {

        in.defbultRebdObject();

        switch (in.rebd()) {
        cbse 1:
            finbl String[] nbmes = (String[])in.rebdObject();

            finbl Object[] vblues = (Object[]) in.rebdObject();
            descriptor = (nbmes.length == 0) ?
                ImmutbbleDescriptor.EMPTY_DESCRIPTOR :
                new ImmutbbleDescriptor(nbmes, vblues);

            brebk;
        cbse 0:
            descriptor = (Descriptor)in.rebdObject();

            if (descriptor == null) {
                descriptor = ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
            }

            brebk;
        cbse -1: // from bn ebrlier version of the JMX API
            descriptor = ImmutbbleDescriptor.EMPTY_DESCRIPTOR;

            brebk;
        defbult:
            throw new StrebmCorruptedException("Got unexpected byte.");
        }
    }
}
