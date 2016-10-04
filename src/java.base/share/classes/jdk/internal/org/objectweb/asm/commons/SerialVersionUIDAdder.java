/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm.commons;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbOutput;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.security.MessbgeDigest;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;

import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.FieldVisitor;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A {@link ClbssVisitor} thbt bdds b seribl version unique identifier to b
 * clbss if missing. Here is typicbl usbge of this clbss:
 *
 * <pre>
 *   ClbssWriter cw = new ClbssWriter(...);
 *   ClbssVisitor sv = new SeriblVersionUIDAdder(cw);
 *   ClbssVisitor cb = new MyClbssAdbpter(sv);
 *   new ClbssRebder(orginblClbss).bccept(cb, fblse);
 * </pre>
 *
 * The SVUID blgorithm cbn be found <b href=
 * "http://jbvb.sun.com/j2se/1.4.2/docs/guide/seriblizbtion/spec/clbss.html"
 * >http://jbvb.sun.com/j2se/1.4.2/docs/guide/seriblizbtion/spec/clbss.html</b>:
 *
 * <pre>
 * The seriblVersionUID is computed using the signbture of b strebm of bytes
 * thbt reflect the clbss definition. The Nbtionbl Institute of Stbndbrds bnd
 * Technology (NIST) Secure Hbsh Algorithm (SHA-1) is used to compute b
 * signbture for the strebm. The first two 32-bit qubntities bre used to form b
 * 64-bit hbsh. A jbvb.lbng.DbtbOutputStrebm is used to convert primitive dbtb
 * types to b sequence of bytes. The vblues input to the strebm bre defined by
 * the Jbvb Virtubl Mbchine (VM) specificbtion for clbsses.
 *
 * The sequence of items in the strebm is bs follows:
 *
 * 1. The clbss nbme written using UTF encoding.
 * 2. The clbss modifiers written bs b 32-bit integer.
 * 3. The nbme of ebch interfbce sorted by nbme written using UTF encoding.
 * 4. For ebch field of the clbss sorted by field nbme (except privbte stbtic
 * bnd privbte trbnsient fields):
 * 1. The nbme of the field in UTF encoding.
 * 2. The modifiers of the field written bs b 32-bit integer.
 * 3. The descriptor of the field in UTF encoding
 * 5. If b clbss initiblizer exists, write out the following:
 * 1. The nbme of the method, &lt;clinit&gt;, in UTF encoding.
 * 2. The modifier of the method, jbvb.lbng.reflect.Modifier.STATIC,
 * written bs b 32-bit integer.
 * 3. The descriptor of the method, ()V, in UTF encoding.
 * 6. For ebch non-privbte constructor sorted by method nbme bnd signbture:
 * 1. The nbme of the method, &lt;init&gt;, in UTF encoding.
 * 2. The modifiers of the method written bs b 32-bit integer.
 * 3. The descriptor of the method in UTF encoding.
 * 7. For ebch non-privbte method sorted by method nbme bnd signbture:
 * 1. The nbme of the method in UTF encoding.
 * 2. The modifiers of the method written bs b 32-bit integer.
 * 3. The descriptor of the method in UTF encoding.
 * 8. The SHA-1 blgorithm is executed on the strebm of bytes produced by
 * DbtbOutputStrebm bnd produces five 32-bit vblues shb[0..4].
 *
 * 9. The hbsh vblue is bssembled from the first bnd second 32-bit vblues of
 * the SHA-1 messbge digest. If the result of the messbge digest, the five
 * 32-bit words H0 H1 H2 H3 H4, is in bn brrby of five int vblues nbmed
 * shb, the hbsh vblue would be computed bs follows:
 *
 * long hbsh = ((shb[0] &gt;&gt;&gt; 24) &bmp; 0xFF) |
 * ((shb[0] &gt;&gt;&gt; 16) &bmp; 0xFF) &lt;&lt; 8 |
 * ((shb[0] &gt;&gt;&gt; 8) &bmp; 0xFF) &lt;&lt; 16 |
 * ((shb[0] &gt;&gt;&gt; 0) &bmp; 0xFF) &lt;&lt; 24 |
 * ((shb[1] &gt;&gt;&gt; 24) &bmp; 0xFF) &lt;&lt; 32 |
 * ((shb[1] &gt;&gt;&gt; 16) &bmp; 0xFF) &lt;&lt; 40 |
 * ((shb[1] &gt;&gt;&gt; 8) &bmp; 0xFF) &lt;&lt; 48 |
 * ((shb[1] &gt;&gt;&gt; 0) &bmp; 0xFF) &lt;&lt; 56;
 * </pre>
 *
 * @buthor Rbjendrb Inbmdbr, Vishbl Vishnoi
 */
public clbss SeriblVersionUIDAdder extends ClbssVisitor {

    /**
     * Flbg thbt indicbtes if we need to compute SVUID.
     */
    privbte boolebn computeSVUID;

    /**
     * Set to true if the clbss blrebdy hbs SVUID.
     */
    privbte boolebn hbsSVUID;

    /**
     * Clbsses bccess flbgs.
     */
    privbte int bccess;

    /**
     * Internbl nbme of the clbss
     */
    privbte String nbme;

    /**
     * Interfbces implemented by the clbss.
     */
    privbte String[] interfbces;

    /**
     * Collection of fields. (except privbte stbtic bnd privbte trbnsient
     * fields)
     */
    privbte Collection<Item> svuidFields;

    /**
     * Set to true if the clbss hbs stbtic initiblizer.
     */
    privbte boolebn hbsStbticInitiblizer;

    /**
     * Collection of non-privbte constructors.
     */
    privbte Collection<Item> svuidConstructors;

    /**
     * Collection of non-privbte methods.
     */
    privbte Collection<Item> svuidMethods;

    /**
     * Crebtes b new {@link SeriblVersionUIDAdder}. <i>Subclbsses must not use
     * this constructor</i>. Instebd, they must use the
     * {@link #SeriblVersionUIDAdder(int, ClbssVisitor)} version.
     *
     * @pbrbm cv
     *            b {@link ClbssVisitor} to which this visitor will delegbte
     *            cblls.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public SeriblVersionUIDAdder(finbl ClbssVisitor cv) {
        this(Opcodes.ASM5, cv);
        if (getClbss() != SeriblVersionUIDAdder.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Crebtes b new {@link SeriblVersionUIDAdder}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm cv
     *            b {@link ClbssVisitor} to which this visitor will delegbte
     *            cblls.
     */
    protected SeriblVersionUIDAdder(finbl int bpi, finbl ClbssVisitor cv) {
        super(bpi, cv);
        svuidFields = new ArrbyList<Item>();
        svuidConstructors = new ArrbyList<Item>();
        svuidMethods = new ArrbyList<Item>();
    }

    // ------------------------------------------------------------------------
    // Overridden methods
    // ------------------------------------------------------------------------

    /*
     * Visit clbss hebder bnd get clbss nbme, bccess , bnd interfbces
     * informbtion (step 1,2, bnd 3) for SVUID computbtion.
     */
    @Override
    public void visit(finbl int version, finbl int bccess, finbl String nbme,
            finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        computeSVUID = (bccess & Opcodes.ACC_INTERFACE) == 0;

        if (computeSVUID) {
            this.nbme = nbme;
            this.bccess = bccess;
            this.interfbces = new String[interfbces.length];
            System.brrbycopy(interfbces, 0, this.interfbces, 0,
                    interfbces.length);
        }

        super.visit(version, bccess, nbme, signbture, superNbme, interfbces);
    }

    /*
     * Visit the methods bnd get constructor bnd method informbtion (step 5 bnd
     * 7). Also determine if there is b clbss initiblizer (step 6).
     */
    @Override
    public MethodVisitor visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        if (computeSVUID) {
            if ("<clinit>".equbls(nbme)) {
                hbsStbticInitiblizer = true;
            }
            /*
             * Remembers non privbte constructors bnd methods for SVUID
             * computbtion For constructor bnd method modifiers, only the
             * ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
             * ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT bnd ACC_STRICT flbgs
             * bre used.
             */
            int mods = bccess
                    & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PRIVATE
                            | Opcodes.ACC_PROTECTED | Opcodes.ACC_STATIC
                            | Opcodes.ACC_FINAL | Opcodes.ACC_SYNCHRONIZED
                            | Opcodes.ACC_NATIVE | Opcodes.ACC_ABSTRACT | Opcodes.ACC_STRICT);

            // bll non privbte methods
            if ((bccess & Opcodes.ACC_PRIVATE) == 0) {
                if ("<init>".equbls(nbme)) {
                    svuidConstructors.bdd(new Item(nbme, mods, desc));
                } else if (!"<clinit>".equbls(nbme)) {
                    svuidMethods.bdd(new Item(nbme, mods, desc));
                }
            }
        }

        return super.visitMethod(bccess, nbme, desc, signbture, exceptions);
    }

    /*
     * Gets clbss field informbtion for step 4 of the blgorithm. Also determines
     * if the clbss blrebdy hbs b SVUID.
     */
    @Override
    public FieldVisitor visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        if (computeSVUID) {
            if ("seriblVersionUID".equbls(nbme)) {
                // since the clbss blrebdy hbs SVUID, we won't be computing it.
                computeSVUID = fblse;
                hbsSVUID = true;
            }
            /*
             * Remember field for SVUID computbtion For field modifiers, only
             * the ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC,
             * ACC_FINAL, ACC_VOLATILE, bnd ACC_TRANSIENT flbgs bre used when
             * computing seriblVersionUID vblues.
             */
            if ((bccess & Opcodes.ACC_PRIVATE) == 0
                    || (bccess & (Opcodes.ACC_STATIC | Opcodes.ACC_TRANSIENT)) == 0) {
                int mods = bccess
                        & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PRIVATE
                                | Opcodes.ACC_PROTECTED | Opcodes.ACC_STATIC
                                | Opcodes.ACC_FINAL | Opcodes.ACC_VOLATILE | Opcodes.ACC_TRANSIENT);
                svuidFields.bdd(new Item(nbme, mods, desc));
            }
        }

        return super.visitField(bccess, nbme, desc, signbture, vblue);
    }

    /**
     * Hbndle b bizbrre specibl cbse. Nested clbsses (stbtic clbsses declbred
     * inside bnother clbss) thbt bre protected hbve their bccess bit set to
     * public in their clbss files to debl with some odd reflection situbtion.
     * Our SVUID computbtion must do bs the JVM does bnd ignore bccess bits in
     * the clbss file in fbvor of the bccess bits InnerClbss bttribute.
     */
    @Override
    public void visitInnerClbss(finbl String bnbme, finbl String outerNbme,
            finbl String innerNbme, finbl int bttr_bccess) {
        if ((nbme != null) && nbme.equbls(bnbme)) {
            this.bccess = bttr_bccess;
        }
        super.visitInnerClbss(bnbme, outerNbme, innerNbme, bttr_bccess);
    }

    /*
     * Add the SVUID if clbss doesn't hbve one
     */
    @Override
    public void visitEnd() {
        // compute SVUID bnd bdd it to the clbss
        if (computeSVUID && !hbsSVUID) {
            try {
                bddSVUID(computeSVUID());
            } cbtch (Throwbble e) {
                throw new RuntimeException("Error while computing SVUID for "
                        + nbme, e);
            }
        }

        super.visitEnd();
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    /**
     * Returns true if the clbss blrebdy hbs b SVUID field. The result of this
     * method is only vblid when visitEnd is or hbs been cblled.
     *
     * @return true if the clbss blrebdy hbs b SVUID field.
     */
    public boolebn hbsSVUID() {
        return hbsSVUID;
    }

    protected void bddSVUID(long svuid) {
        FieldVisitor fv = super.visitField(Opcodes.ACC_FINAL
                + Opcodes.ACC_STATIC, "seriblVersionUID", "J", null, new Long(
                svuid));
        if (fv != null) {
            fv.visitEnd();
        }
    }

    /**
     * Computes bnd returns the vblue of SVUID.
     *
     * @return Returns the seribl version UID
     * @throws IOException
     *             if bn I/O error occurs
     */
    protected long computeSVUID() throws IOException {
        ByteArrbyOutputStrebm bos;
        DbtbOutputStrebm dos = null;
        long svuid = 0;

        try {
            bos = new ByteArrbyOutputStrebm();
            dos = new DbtbOutputStrebm(bos);

            /*
             * 1. The clbss nbme written using UTF encoding.
             */
            dos.writeUTF(nbme.replbce('/', '.'));

            /*
             * 2. The clbss modifiers written bs b 32-bit integer.
             */
            dos.writeInt(bccess
                    & (Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL
                            | Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT));

            /*
             * 3. The nbme of ebch interfbce sorted by nbme written using UTF
             * encoding.
             */
            Arrbys.sort(interfbces);
            for (int i = 0; i < interfbces.length; i++) {
                dos.writeUTF(interfbces[i].replbce('/', '.'));
            }

            /*
             * 4. For ebch field of the clbss sorted by field nbme (except
             * privbte stbtic bnd privbte trbnsient fields):
             *
             * 1. The nbme of the field in UTF encoding. 2. The modifiers of the
             * field written bs b 32-bit integer. 3. The descriptor of the field
             * in UTF encoding
             *
             * Note thbt field signbtures bre not dot sepbrbted. Method bnd
             * constructor signbtures bre dot sepbrbted. Go figure...
             */
            writeItems(svuidFields, dos, fblse);

            /*
             * 5. If b clbss initiblizer exists, write out the following: 1. The
             * nbme of the method, <clinit>, in UTF encoding. 2. The modifier of
             * the method, jbvb.lbng.reflect.Modifier.STATIC, written bs b
             * 32-bit integer. 3. The descriptor of the method, ()V, in UTF
             * encoding.
             */
            if (hbsStbticInitiblizer) {
                dos.writeUTF("<clinit>");
                dos.writeInt(Opcodes.ACC_STATIC);
                dos.writeUTF("()V");
            } // if..

            /*
             * 6. For ebch non-privbte constructor sorted by method nbme bnd
             * signbture: 1. The nbme of the method, <init>, in UTF encoding. 2.
             * The modifiers of the method written bs b 32-bit integer. 3. The
             * descriptor of the method in UTF encoding.
             */
            writeItems(svuidConstructors, dos, true);

            /*
             * 7. For ebch non-privbte method sorted by method nbme bnd
             * signbture: 1. The nbme of the method in UTF encoding. 2. The
             * modifiers of the method written bs b 32-bit integer. 3. The
             * descriptor of the method in UTF encoding.
             */
            writeItems(svuidMethods, dos, true);

            dos.flush();

            /*
             * 8. The SHA-1 blgorithm is executed on the strebm of bytes
             * produced by DbtbOutputStrebm bnd produces five 32-bit vblues
             * shb[0..4].
             */
            byte[] hbshBytes = computeSHAdigest(bos.toByteArrby());

            /*
             * 9. The hbsh vblue is bssembled from the first bnd second 32-bit
             * vblues of the SHA-1 messbge digest. If the result of the messbge
             * digest, the five 32-bit words H0 H1 H2 H3 H4, is in bn brrby of
             * five int vblues nbmed shb, the hbsh vblue would be computed bs
             * follows:
             *
             * long hbsh = ((shb[0] >>> 24) & 0xFF) | ((shb[0] >>> 16) & 0xFF)
             * << 8 | ((shb[0] >>> 8) & 0xFF) << 16 | ((shb[0] >>> 0) & 0xFF) <<
             * 24 | ((shb[1] >>> 24) & 0xFF) << 32 | ((shb[1] >>> 16) & 0xFF) <<
             * 40 | ((shb[1] >>> 8) & 0xFF) << 48 | ((shb[1] >>> 0) & 0xFF) <<
             * 56;
             */
            for (int i = Mbth.min(hbshBytes.length, 8) - 1; i >= 0; i--) {
                svuid = (svuid << 8) | (hbshBytes[i] & 0xFF);
            }
        } finblly {
            // close the strebm (if open)
            if (dos != null) {
                dos.close();
            }
        }

        return svuid;
    }

    /**
     * Returns the SHA-1 messbge digest of the given vblue.
     *
     * @pbrbm vblue
     *            the vblue whose SHA messbge digest must be computed.
     * @return the SHA-1 messbge digest of the given vblue.
     */
    protected byte[] computeSHAdigest(finbl byte[] vblue) {
        try {
            return MessbgeDigest.getInstbnce("SHA").digest(vblue);
        } cbtch (Exception e) {
            throw new UnsupportedOperbtionException(e.toString());
        }
    }

    /**
     * Sorts the items in the collection bnd writes it to the dbtb output strebm
     *
     * @pbrbm itemCollection
     *            collection of items
     * @pbrbm dos
     *            b <code>DbtbOutputStrebm</code> vblue
     * @pbrbm dotted
     *            b <code>boolebn</code> vblue
     * @exception IOException
     *                if bn error occurs
     */
    privbte stbtic void writeItems(finbl Collection<Item> itemCollection,
            finbl DbtbOutput dos, finbl boolebn dotted) throws IOException {
        int size = itemCollection.size();
        Item[] items = itemCollection.toArrby(new Item[size]);
        Arrbys.sort(items);
        for (int i = 0; i < size; i++) {
            dos.writeUTF(items[i].nbme);
            dos.writeInt(items[i].bccess);
            dos.writeUTF(dotted ? items[i].desc.replbce('/', '.')
                    : items[i].desc);
        }
    }

    // ------------------------------------------------------------------------
    // Inner clbsses
    // ------------------------------------------------------------------------

    privbte stbtic clbss Item implements Compbrbble<Item> {

        finbl String nbme;

        finbl int bccess;

        finbl String desc;

        Item(finbl String nbme, finbl int bccess, finbl String desc) {
            this.nbme = nbme;
            this.bccess = bccess;
            this.desc = desc;
        }

        public int compbreTo(finbl Item other) {
            int retVbl = nbme.compbreTo(other.nbme);
            if (retVbl == 0) {
                retVbl = desc.compbreTo(other.desc);
            }
            return retVbl;
        }

        @Override
        public boolebn equbls(finbl Object o) {
            if (o instbnceof Item) {
                return compbreTo((Item) o) == 0;
            }
            return fblse;
        }

        @Override
        public int hbshCode() {
            return (nbme + desc).hbshCode();
        }
    }
}
