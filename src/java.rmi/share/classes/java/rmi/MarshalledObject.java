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

pbckbge jbvb.rmi;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmConstbnts;
import jbvb.io.OutputStrebm;
import jbvb.io.Seriblizbble;
import sun.rmi.server.MbrshblInputStrebm;
import sun.rmi.server.MbrshblOutputStrebm;

/**
 * A <code>MbrshblledObject</code> contbins b byte strebm with the seriblized
 * representbtion of bn object given to its constructor.  The <code>get</code>
 * method returns b new copy of the originbl object, bs deseriblized from
 * the contbined byte strebm.  The contbined object is seriblized bnd
 * deseriblized with the sbme seriblizbtion sembntics used for mbrshbling
 * bnd unmbrshbling pbrbmeters bnd return vblues of RMI cblls:  When the
 * seriblized form is crebted:
 *
 * <ul>
 * <li> clbsses bre bnnotbted with b codebbse URL from where the clbss
 *      cbn be lobded (if bvbilbble), bnd
 * <li> bny remote object in the <code>MbrshblledObject</code> is
 *      represented by b seriblized instbnce of its stub.
 * </ul>
 *
 * <p>When copy of the object is retrieved (vib the <code>get</code> method),
 * if the clbss is not bvbilbble locblly, it will be lobded from the
 * bppropribte locbtion (specified the URL bnnotbted with the clbss descriptor
 * when the clbss wbs seriblized.
 *
 * <p><code>MbrshblledObject</code> fbcilitbtes pbssing objects in RMI cblls
 * thbt bre not butombticblly deseriblized immedibtely by the remote peer.
 *
 * @pbrbm <T> the type of the object contbined in this
 * <code>MbrshblledObject</code>
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 * @since   1.2
 */
public finbl clbss MbrshblledObject<T> implements Seriblizbble {
    /**
     * @seribl Bytes of seriblized representbtion.  If <code>objBytes</code> is
     * <code>null</code> then the object mbrshblled wbs b <code>null</code>
     * reference.
     */
    privbte byte[] objBytes = null;

    /**
     * @seribl Bytes of locbtion bnnotbtions, which bre ignored by
     * <code>equbls</code>.  If <code>locBytes</code> is null, there were no
     * non-<code>null</code> bnnotbtions during mbrshblling.
     */
    privbte byte[] locBytes = null;

    /**
     * @seribl Stored hbsh code of contbined object.
     *
     * @see #hbshCode
     */
    privbte int hbsh;

    /** Indicbte compbtibility with 1.2 version of clbss. */
    privbte stbtic finbl long seriblVersionUID = 8988374069173025854L;

    /**
     * Crebtes b new <code>MbrshblledObject</code> thbt contbins the
     * seriblized representbtion of the current stbte of the supplied object.
     * The object is seriblized with the sembntics used for mbrshbling
     * pbrbmeters for RMI cblls.
     *
     * @pbrbm obj the object to be seriblized (must be seriblizbble)
     * @exception IOException if bn <code>IOException</code> occurs; bn
     * <code>IOException</code> mby occur if <code>obj</code> is not
     * seriblizbble.
     * @since 1.2
     */
    public MbrshblledObject(T obj) throws IOException {
        if (obj == null) {
            hbsh = 13;
            return;
        }

        ByteArrbyOutputStrebm bout = new ByteArrbyOutputStrebm();
        ByteArrbyOutputStrebm lout = new ByteArrbyOutputStrebm();
        MbrshblledObjectOutputStrebm out =
            new MbrshblledObjectOutputStrebm(bout, lout);
        out.writeObject(obj);
        out.flush();
        objBytes = bout.toByteArrby();
        // locBytes is null if no bnnotbtions
        locBytes = (out.hbdAnnotbtions() ? lout.toByteArrby() : null);

        /*
         * Cblculbte hbsh from the mbrshblled representbtion of object
         * so the hbshcode will be compbrbble when sent between VMs.
         */
        int h = 0;
        for (int i = 0; i < objBytes.length; i++) {
            h = 31 * h + objBytes[i];
        }
        hbsh = h;
    }

    /**
     * Returns b new copy of the contbined mbrshblledobject.  The internbl
     * representbtion is deseriblized with the sembntics used for
     * unmbrshbling pbrbmeters for RMI cblls.
     *
     * @return b copy of the contbined object
     * @exception IOException if bn <code>IOException</code> occurs while
     * deseriblizing the object from its internbl representbtion.
     * @exception ClbssNotFoundException if b
     * <code>ClbssNotFoundException</code> occurs while deseriblizing the
     * object from its internbl representbtion.
     * could not be found
     * @since 1.2
     */
    public T get() throws IOException, ClbssNotFoundException {
        if (objBytes == null)   // must hbve been b null object
            return null;

        ByteArrbyInputStrebm bin = new ByteArrbyInputStrebm(objBytes);
        // locBytes is null if no bnnotbtions
        ByteArrbyInputStrebm lin =
            (locBytes == null ? null : new ByteArrbyInputStrebm(locBytes));
        MbrshblledObjectInputStrebm in =
            new MbrshblledObjectInputStrebm(bin, lin);
        @SuppressWbrnings("unchecked")
        T obj = (T) in.rebdObject();
        in.close();
        return obj;
    }

    /**
     * Return b hbsh code for this <code>MbrshblledObject</code>.
     *
     * @return b hbsh code
     */
    public int hbshCode() {
        return hbsh;
    }

    /**
     * Compbres this <code>MbrshblledObject</code> to bnother object.
     * Returns true if bnd only if the brgument refers to b
     * <code>MbrshblledObject</code> thbt contbins exbctly the sbme
     * seriblized representbtion of bn object bs this one does. The
     * compbrison ignores bny clbss codebbse bnnotbtion, mebning thbt
     * two objects bre equivblent if they hbve the sbme seriblized
     * representbtion <i>except</i> for the codebbse of ebch clbss
     * in the seriblized representbtion.
     *
     * @pbrbm obj the object to compbre with this <code>MbrshblledObject</code>
     * @return <code>true</code> if the brgument contbins bn equivblent
     * seriblized object; <code>fblse</code> otherwise
     * @since 1.2
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (obj != null && obj instbnceof MbrshblledObject) {
            MbrshblledObject<?> other = (MbrshblledObject<?>) obj;

            // if either is b ref to null, both must be
            if (objBytes == null || other.objBytes == null)
                return objBytes == other.objBytes;

            // quick, ebsy test
            if (objBytes.length != other.objBytes.length)
                return fblse;

            //!! There is tblk bbout bdding bn brrby compbrision method
            //!! bt 1.2 -- if so, this should be rewritten.  -brnold
            for (int i = 0; i < objBytes.length; ++i) {
                if (objBytes[i] != other.objBytes[i])
                    return fblse;
            }
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * This clbss is used to mbrshbl objects for
     * <code>MbrshblledObject</code>.  It plbces the locbtion bnnotbtions
     * to one side so thbt two <code>MbrshblledObject</code>s cbn be
     * compbred for equblity if they differ only in locbtion
     * bnnotbtions.  Objects written using this strebm should be rebd bbck
     * from b <code>MbrshblledObjectInputStrebm</code>.
     *
     * @see jbvb.rmi.MbrshblledObject
     * @see MbrshblledObjectInputStrebm
     */
    privbte stbtic clbss MbrshblledObjectOutputStrebm
        extends MbrshblOutputStrebm
    {
        /** The strebm on which locbtion objects bre written. */
        privbte ObjectOutputStrebm locOut;

        /** <code>true</code> if non-<code>null</code> bnnotbtions bre
         *  written.
         */
        privbte boolebn hbdAnnotbtions;

        /**
         * Crebtes b new <code>MbrshblledObjectOutputStrebm</code> whose
         * non-locbtion bytes will be written to <code>objOut</code> bnd whose
         * locbtion bnnotbtions (if bny) will be written to
         * <code>locOut</code>.
         */
        MbrshblledObjectOutputStrebm(OutputStrebm objOut, OutputStrebm locOut)
            throws IOException
        {
            super(objOut);
            this.useProtocolVersion(ObjectStrebmConstbnts.PROTOCOL_VERSION_2);
            this.locOut = new ObjectOutputStrebm(locOut);
            hbdAnnotbtions = fblse;
        }

        /**
         * Returns <code>true</code> if bny non-<code>null</code> locbtion
         * bnnotbtions hbve been written to this strebm.
         */
        boolebn hbdAnnotbtions() {
            return hbdAnnotbtions;
        }

        /**
         * Overrides MbrshblOutputStrebm.writeLocbtion implementbtion to write
         * bnnotbtions to the locbtion strebm.
         */
        protected void writeLocbtion(String loc) throws IOException {
            hbdAnnotbtions |= (loc != null);
            locOut.writeObject(loc);
        }


        public void flush() throws IOException {
            super.flush();
            locOut.flush();
        }
    }

    /**
     * The counterpbrt to <code>MbrshblledObjectOutputStrebm</code>.
     *
     * @see MbrshblledObjectOutputStrebm
     */
    privbte stbtic clbss MbrshblledObjectInputStrebm
        extends MbrshblInputStrebm
    {
        /**
         * The strebm from which bnnotbtions will be rebd.  If this is
         * <code>null</code>, then bll bnnotbtions were <code>null</code>.
         */
        privbte ObjectInputStrebm locIn;

        /**
         * Crebtes b new <code>MbrshblledObjectInputStrebm</code> thbt
         * rebds its objects from <code>objIn</code> bnd bnnotbtions
         * from <code>locIn</code>.  If <code>locIn</code> is
         * <code>null</code>, then bll bnnotbtions will be
         * <code>null</code>.
         */
        MbrshblledObjectInputStrebm(InputStrebm objIn, InputStrebm locIn)
            throws IOException
        {
            super(objIn);
            this.locIn = (locIn == null ? null : new ObjectInputStrebm(locIn));
        }

        /**
         * Overrides MbrshblInputStrebm.rebdLocbtion to return locbtions from
         * the strebm we were given, or <code>null</code> if we were given b
         * <code>null</code> locbtion strebm.
         */
        protected Object rebdLocbtion()
            throws IOException, ClbssNotFoundException
        {
            return (locIn == null ? null : locIn.rebdObject());
        }
    }

}
