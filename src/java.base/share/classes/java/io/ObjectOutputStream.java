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

import jbvb.io.ObjectStrebmClbss.WebkClbssKey;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import stbtic jbvb.io.ObjectStrebmClbss.processQueue;
import sun.reflect.misc.ReflectUtil;

/**
 * An ObjectOutputStrebm writes primitive dbtb types bnd grbphs of Jbvb objects
 * to bn OutputStrebm.  The objects cbn be rebd (reconstituted) using bn
 * ObjectInputStrebm.  Persistent storbge of objects cbn be bccomplished by
 * using b file for the strebm.  If the strebm is b network socket strebm, the
 * objects cbn be reconstituted on bnother host or in bnother process.
 *
 * <p>Only objects thbt support the jbvb.io.Seriblizbble interfbce cbn be
 * written to strebms.  The clbss of ebch seriblizbble object is encoded
 * including the clbss nbme bnd signbture of the clbss, the vblues of the
 * object's fields bnd brrbys, bnd the closure of bny other objects referenced
 * from the initibl objects.
 *
 * <p>The method writeObject is used to write bn object to the strebm.  Any
 * object, including Strings bnd brrbys, is written with writeObject. Multiple
 * objects or primitives cbn be written to the strebm.  The objects must be
 * rebd bbck from the corresponding ObjectInputstrebm with the sbme types bnd
 * in the sbme order bs they were written.
 *
 * <p>Primitive dbtb types cbn blso be written to the strebm using the
 * bppropribte methods from DbtbOutput. Strings cbn blso be written using the
 * writeUTF method.
 *
 * <p>The defbult seriblizbtion mechbnism for bn object writes the clbss of the
 * object, the clbss signbture, bnd the vblues of bll non-trbnsient bnd
 * non-stbtic fields.  References to other objects (except in trbnsient or
 * stbtic fields) cbuse those objects to be written blso. Multiple references
 * to b single object bre encoded using b reference shbring mechbnism so thbt
 * grbphs of objects cbn be restored to the sbme shbpe bs when the originbl wbs
 * written.
 *
 * <p>For exbmple to write bn object thbt cbn be rebd by the exbmple in
 * ObjectInputStrebm:
 * <br>
 * <pre>
 *      FileOutputStrebm fos = new FileOutputStrebm("t.tmp");
 *      ObjectOutputStrebm oos = new ObjectOutputStrebm(fos);
 *
 *      oos.writeInt(12345);
 *      oos.writeObject("Todby");
 *      oos.writeObject(new Dbte());
 *
 *      oos.close();
 * </pre>
 *
 * <p>Clbsses thbt require specibl hbndling during the seriblizbtion bnd
 * deseriblizbtion process must implement specibl methods with these exbct
 * signbtures:
 * <br>
 * <pre>
 * privbte void rebdObject(jbvb.io.ObjectInputStrebm strebm)
 *     throws IOException, ClbssNotFoundException;
 * privbte void writeObject(jbvb.io.ObjectOutputStrebm strebm)
 *     throws IOException
 * privbte void rebdObjectNoDbtb()
 *     throws ObjectStrebmException;
 * </pre>
 *
 * <p>The writeObject method is responsible for writing the stbte of the object
 * for its pbrticulbr clbss so thbt the corresponding rebdObject method cbn
 * restore it.  The method does not need to concern itself with the stbte
 * belonging to the object's superclbsses or subclbsses.  Stbte is sbved by
 * writing the individubl fields to the ObjectOutputStrebm using the
 * writeObject method or by using the methods for primitive dbtb types
 * supported by DbtbOutput.
 *
 * <p>Seriblizbtion does not write out the fields of bny object thbt does not
 * implement the jbvb.io.Seriblizbble interfbce.  Subclbsses of Objects thbt
 * bre not seriblizbble cbn be seriblizbble. In this cbse the non-seriblizbble
 * clbss must hbve b no-brg constructor to bllow its fields to be initiblized.
 * In this cbse it is the responsibility of the subclbss to sbve bnd restore
 * the stbte of the non-seriblizbble clbss. It is frequently the cbse thbt the
 * fields of thbt clbss bre bccessible (public, pbckbge, or protected) or thbt
 * there bre get bnd set methods thbt cbn be used to restore the stbte.
 *
 * <p>Seriblizbtion of bn object cbn be prevented by implementing writeObject
 * bnd rebdObject methods thbt throw the NotSeriblizbbleException.  The
 * exception will be cbught by the ObjectOutputStrebm bnd bbort the
 * seriblizbtion process.
 *
 * <p>Implementing the Externblizbble interfbce bllows the object to bssume
 * complete control over the contents bnd formbt of the object's seriblized
 * form.  The methods of the Externblizbble interfbce, writeExternbl bnd
 * rebdExternbl, bre cblled to sbve bnd restore the objects stbte.  When
 * implemented by b clbss they cbn write bnd rebd their own stbte using bll of
 * the methods of ObjectOutput bnd ObjectInput.  It is the responsibility of
 * the objects to hbndle bny versioning thbt occurs.
 *
 * <p>Enum constbnts bre seriblized differently thbn ordinbry seriblizbble or
 * externblizbble objects.  The seriblized form of bn enum constbnt consists
 * solely of its nbme; field vblues of the constbnt bre not trbnsmitted.  To
 * seriblize bn enum constbnt, ObjectOutputStrebm writes the string returned by
 * the constbnt's nbme method.  Like other seriblizbble or externblizbble
 * objects, enum constbnts cbn function bs the tbrgets of bbck references
 * bppebring subsequently in the seriblizbtion strebm.  The process by which
 * enum constbnts bre seriblized cbnnot be customized; bny clbss-specific
 * writeObject bnd writeReplbce methods defined by enum types bre ignored
 * during seriblizbtion.  Similbrly, bny seriblPersistentFields or
 * seriblVersionUID field declbrbtions bre blso ignored--bll enum types hbve b
 * fixed seriblVersionUID of 0L.
 *
 * <p>Primitive dbtb, excluding seriblizbble fields bnd externblizbble dbtb, is
 * written to the ObjectOutputStrebm in block-dbtb records. A block dbtb record
 * is composed of b hebder bnd dbtb. The block dbtb hebder consists of b mbrker
 * bnd the number of bytes to follow the hebder.  Consecutive primitive dbtb
 * writes bre merged into one block-dbtb record.  The blocking fbctor used for
 * b block-dbtb record will be 1024 bytes.  Ebch block-dbtb record will be
 * filled up to 1024 bytes, or be written whenever there is b terminbtion of
 * block-dbtb mode.  Cblls to the ObjectOutputStrebm methods writeObject,
 * defbultWriteObject bnd writeFields initiblly terminbte bny existing
 * block-dbtb record.
 *
 * @buthor      Mike Wbrres
 * @buthor      Roger Riggs
 * @see jbvb.io.DbtbOutput
 * @see jbvb.io.ObjectInputStrebm
 * @see jbvb.io.Seriblizbble
 * @see jbvb.io.Externblizbble
 * @see <b href="../../../plbtform/seriblizbtion/spec/output.html">Object Seriblizbtion Specificbtion, Section 2, Object Output Clbsses</b>
 * @since       1.1
 */
public clbss ObjectOutputStrebm
    extends OutputStrebm implements ObjectOutput, ObjectStrebmConstbnts
{

    privbte stbtic clbss Cbches {
        /** cbche of subclbss security budit results */
        stbtic finbl ConcurrentMbp<WebkClbssKey,Boolebn> subclbssAudits =
            new ConcurrentHbshMbp<>();

        /** queue for WebkReferences to budited subclbsses */
        stbtic finbl ReferenceQueue<Clbss<?>> subclbssAuditsQueue =
            new ReferenceQueue<>();
    }

    /** filter strebm for hbndling block dbtb conversion */
    privbte finbl BlockDbtbOutputStrebm bout;
    /** obj -> wire hbndle mbp */
    privbte finbl HbndleTbble hbndles;
    /** obj -> replbcement obj mbp */
    privbte finbl ReplbceTbble subs;
    /** strebm protocol version */
    privbte int protocol = PROTOCOL_VERSION_2;
    /** recursion depth */
    privbte int depth;

    /** buffer for writing primitive field vblues */
    privbte byte[] primVbls;

    /** if true, invoke writeObjectOverride() instebd of writeObject() */
    privbte finbl boolebn enbbleOverride;
    /** if true, invoke replbceObject() */
    privbte boolebn enbbleReplbce;

    // vblues below vblid only during upcblls to writeObject()/writeExternbl()
    /**
     * Context during upcblls to clbss-defined writeObject methods; holds
     * object currently being seriblized bnd descriptor for current clbss.
     * Null when not during writeObject upcbll.
     */
    privbte SeriblCbllbbckContext curContext;
    /** current PutField object */
    privbte PutFieldImpl curPut;

    /** custom storbge for debug trbce info */
    privbte finbl DebugTrbceInfoStbck debugInfoStbck;

    /**
     * vblue of "sun.io.seriblizbtion.extendedDebugInfo" property,
     * bs true or fblse for extended informbtion bbout exception's plbce
     */
    privbte stbtic finbl boolebn extendedDebugInfo =
        jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetBoolebnAction(
                "sun.io.seriblizbtion.extendedDebugInfo")).boolebnVblue();

    /**
     * Crebtes bn ObjectOutputStrebm thbt writes to the specified OutputStrebm.
     * This constructor writes the seriblizbtion strebm hebder to the
     * underlying strebm; cbllers mby wish to flush the strebm immedibtely to
     * ensure thbt constructors for receiving ObjectInputStrebms will not block
     * when rebding the hebder.
     *
     * <p>If b security mbnbger is instblled, this constructor will check for
     * the "enbbleSubclbssImplementbtion" SeriblizbblePermission when invoked
     * directly or indirectly by the constructor of b subclbss which overrides
     * the ObjectOutputStrebm.putFields or ObjectOutputStrebm.writeUnshbred
     * methods.
     *
     * @pbrbm   out output strebm to write to
     * @throws  IOException if bn I/O error occurs while writing strebm hebder
     * @throws  SecurityException if untrusted subclbss illegblly overrides
     *          security-sensitive methods
     * @throws  NullPointerException if <code>out</code> is <code>null</code>
     * @since   1.4
     * @see     ObjectOutputStrebm#ObjectOutputStrebm()
     * @see     ObjectOutputStrebm#putFields()
     * @see     ObjectInputStrebm#ObjectInputStrebm(InputStrebm)
     */
    public ObjectOutputStrebm(OutputStrebm out) throws IOException {
        verifySubclbss();
        bout = new BlockDbtbOutputStrebm(out);
        hbndles = new HbndleTbble(10, (flobt) 3.00);
        subs = new ReplbceTbble(10, (flobt) 3.00);
        enbbleOverride = fblse;
        writeStrebmHebder();
        bout.setBlockDbtbMode(true);
        if (extendedDebugInfo) {
            debugInfoStbck = new DebugTrbceInfoStbck();
        } else {
            debugInfoStbck = null;
        }
    }

    /**
     * Provide b wby for subclbsses thbt bre completely reimplementing
     * ObjectOutputStrebm to not hbve to bllocbte privbte dbtb just used by
     * this implementbtion of ObjectOutputStrebm.
     *
     * <p>If there is b security mbnbger instblled, this method first cblls the
     * security mbnbger's <code>checkPermission</code> method with b
     * <code>SeriblizbblePermission("enbbleSubclbssImplementbtion")</code>
     * permission to ensure it's ok to enbble subclbssing.
     *
     * @throws  SecurityException if b security mbnbger exists bnd its
     *          <code>checkPermission</code> method denies enbbling
     *          subclbssing.
     * @throws  IOException if bn I/O error occurs while crebting this strebm
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.io.SeriblizbblePermission
     */
    protected ObjectOutputStrebm() throws IOException, SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
        }
        bout = null;
        hbndles = null;
        subs = null;
        enbbleOverride = true;
        debugInfoStbck = null;
    }

    /**
     * Specify strebm protocol version to use when writing the strebm.
     *
     * <p>This routine provides b hook to enbble the current version of
     * Seriblizbtion to write in b formbt thbt is bbckwbrds compbtible to b
     * previous version of the strebm formbt.
     *
     * <p>Every effort will be mbde to bvoid introducing bdditionbl
     * bbckwbrds incompbtibilities; however, sometimes there is no
     * other blternbtive.
     *
     * @pbrbm   version use ProtocolVersion from jbvb.io.ObjectStrebmConstbnts.
     * @throws  IllegblStbteException if cblled bfter bny objects
     *          hbve been seriblized.
     * @throws  IllegblArgumentException if invblid version is pbssed in.
     * @throws  IOException if I/O errors occur
     * @see jbvb.io.ObjectStrebmConstbnts#PROTOCOL_VERSION_1
     * @see jbvb.io.ObjectStrebmConstbnts#PROTOCOL_VERSION_2
     * @since   1.2
     */
    public void useProtocolVersion(int version) throws IOException {
        if (hbndles.size() != 0) {
            // REMIND: implement better check for pristine strebm?
            throw new IllegblStbteException("strebm non-empty");
        }
        switch (version) {
            cbse PROTOCOL_VERSION_1:
            cbse PROTOCOL_VERSION_2:
                protocol = version;
                brebk;

            defbult:
                throw new IllegblArgumentException(
                    "unknown version: " + version);
        }
    }

    /**
     * Write the specified object to the ObjectOutputStrebm.  The clbss of the
     * object, the signbture of the clbss, bnd the vblues of the non-trbnsient
     * bnd non-stbtic fields of the clbss bnd bll of its supertypes bre
     * written.  Defbult seriblizbtion for b clbss cbn be overridden using the
     * writeObject bnd the rebdObject methods.  Objects referenced by this
     * object bre written trbnsitively so thbt b complete equivblent grbph of
     * objects cbn be reconstructed by bn ObjectInputStrebm.
     *
     * <p>Exceptions bre thrown for problems with the OutputStrebm bnd for
     * clbsses thbt should not be seriblized.  All exceptions bre fbtbl to the
     * OutputStrebm, which is left in bn indeterminbte stbte, bnd it is up to
     * the cbller to ignore or recover the strebm stbte.
     *
     * @throws  InvblidClbssException Something is wrong with b clbss used by
     *          seriblizbtion.
     * @throws  NotSeriblizbbleException Some object to be seriblized does not
     *          implement the jbvb.io.Seriblizbble interfbce.
     * @throws  IOException Any exception thrown by the underlying
     *          OutputStrebm.
     */
    public finbl void writeObject(Object obj) throws IOException {
        if (enbbleOverride) {
            writeObjectOverride(obj);
            return;
        }
        try {
            writeObject0(obj, fblse);
        } cbtch (IOException ex) {
            if (depth == 0) {
                writeFbtblException(ex);
            }
            throw ex;
        }
    }

    /**
     * Method used by subclbsses to override the defbult writeObject method.
     * This method is cblled by trusted subclbsses of ObjectInputStrebm thbt
     * constructed ObjectInputStrebm using the protected no-brg constructor.
     * The subclbss is expected to provide bn override method with the modifier
     * "finbl".
     *
     * @pbrbm   obj object to be written to the underlying strebm
     * @throws  IOException if there bre I/O errors while writing to the
     *          underlying strebm
     * @see #ObjectOutputStrebm()
     * @see #writeObject(Object)
     * @since 1.2
     */
    protected void writeObjectOverride(Object obj) throws IOException {
    }

    /**
     * Writes bn "unshbred" object to the ObjectOutputStrebm.  This method is
     * identicbl to writeObject, except thbt it blwbys writes the given object
     * bs b new, unique object in the strebm (bs opposed to b bbck-reference
     * pointing to b previously seriblized instbnce).  Specificblly:
     * <ul>
     *   <li>An object written vib writeUnshbred is blwbys seriblized in the
     *       sbme mbnner bs b newly bppebring object (bn object thbt hbs not
     *       been written to the strebm yet), regbrdless of whether or not the
     *       object hbs been written previously.
     *
     *   <li>If writeObject is used to write bn object thbt hbs been previously
     *       written with writeUnshbred, the previous writeUnshbred operbtion
     *       is trebted bs if it were b write of b sepbrbte object.  In other
     *       words, ObjectOutputStrebm will never generbte bbck-references to
     *       object dbtb written by cblls to writeUnshbred.
     * </ul>
     * While writing bn object vib writeUnshbred does not in itself gubrbntee b
     * unique reference to the object when it is deseriblized, it bllows b
     * single object to be defined multiple times in b strebm, so thbt multiple
     * cblls to rebdUnshbred by the receiver will not conflict.  Note thbt the
     * rules described bbove only bpply to the bbse-level object written with
     * writeUnshbred, bnd not to bny trbnsitively referenced sub-objects in the
     * object grbph to be seriblized.
     *
     * <p>ObjectOutputStrebm subclbsses which override this method cbn only be
     * constructed in security contexts possessing the
     * "enbbleSubclbssImplementbtion" SeriblizbblePermission; bny bttempt to
     * instbntibte such b subclbss without this permission will cbuse b
     * SecurityException to be thrown.
     *
     * @pbrbm   obj object to write to strebm
     * @throws  NotSeriblizbbleException if bn object in the grbph to be
     *          seriblized does not implement the Seriblizbble interfbce
     * @throws  InvblidClbssException if b problem exists with the clbss of bn
     *          object to be seriblized
     * @throws  IOException if bn I/O error occurs during seriblizbtion
     * @since 1.4
     */
    public void writeUnshbred(Object obj) throws IOException {
        try {
            writeObject0(obj, true);
        } cbtch (IOException ex) {
            if (depth == 0) {
                writeFbtblException(ex);
            }
            throw ex;
        }
    }

    /**
     * Write the non-stbtic bnd non-trbnsient fields of the current clbss to
     * this strebm.  This mby only be cblled from the writeObject method of the
     * clbss being seriblized. It will throw the NotActiveException if it is
     * cblled otherwise.
     *
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          <code>OutputStrebm</code>
     */
    public void defbultWriteObject() throws IOException {
        SeriblCbllbbckContext ctx = curContext;
        if (ctx == null) {
            throw new NotActiveException("not in cbll to writeObject");
        }
        Object curObj = ctx.getObj();
        ObjectStrebmClbss curDesc = ctx.getDesc();
        bout.setBlockDbtbMode(fblse);
        defbultWriteFields(curObj, curDesc);
        bout.setBlockDbtbMode(true);
    }

    /**
     * Retrieve the object used to buffer persistent fields to be written to
     * the strebm.  The fields will be written to the strebm when writeFields
     * method is cblled.
     *
     * @return  bn instbnce of the clbss Putfield thbt holds the seriblizbble
     *          fields
     * @throws  IOException if I/O errors occur
     * @since 1.2
     */
    public ObjectOutputStrebm.PutField putFields() throws IOException {
        if (curPut == null) {
            SeriblCbllbbckContext ctx = curContext;
            if (ctx == null) {
                throw new NotActiveException("not in cbll to writeObject");
            }
            ctx.checkAndSetUsed();
            ObjectStrebmClbss curDesc = ctx.getDesc();
            curPut = new PutFieldImpl(curDesc);
        }
        return curPut;
    }

    /**
     * Write the buffered fields to the strebm.
     *
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     * @throws  NotActiveException Cblled when b clbsses writeObject method wbs
     *          not cblled to write the stbte of the object.
     * @since 1.2
     */
    public void writeFields() throws IOException {
        if (curPut == null) {
            throw new NotActiveException("no current PutField object");
        }
        bout.setBlockDbtbMode(fblse);
        curPut.writeFields();
        bout.setBlockDbtbMode(true);
    }

    /**
     * Reset will disregbrd the stbte of bny objects blrebdy written to the
     * strebm.  The stbte is reset to be the sbme bs b new ObjectOutputStrebm.
     * The current point in the strebm is mbrked bs reset so the corresponding
     * ObjectInputStrebm will be reset bt the sbme point.  Objects previously
     * written to the strebm will not be referred to bs blrebdy being in the
     * strebm.  They will be written to the strebm bgbin.
     *
     * @throws  IOException if reset() is invoked while seriblizing bn object.
     */
    public void reset() throws IOException {
        if (depth != 0) {
            throw new IOException("strebm bctive");
        }
        bout.setBlockDbtbMode(fblse);
        bout.writeByte(TC_RESET);
        clebr();
        bout.setBlockDbtbMode(true);
    }

    /**
     * Subclbsses mby implement this method to bllow clbss dbtb to be stored in
     * the strebm. By defbult this method does nothing.  The corresponding
     * method in ObjectInputStrebm is resolveClbss.  This method is cblled
     * exbctly once for ebch unique clbss in the strebm.  The clbss nbme bnd
     * signbture will hbve blrebdy been written to the strebm.  This method mby
     * mbke free use of the ObjectOutputStrebm to sbve bny representbtion of
     * the clbss it deems suitbble (for exbmple, the bytes of the clbss file).
     * The resolveClbss method in the corresponding subclbss of
     * ObjectInputStrebm must rebd bnd use bny dbtb or objects written by
     * bnnotbteClbss.
     *
     * @pbrbm   cl the clbss to bnnotbte custom dbtb for
     * @throws  IOException Any exception thrown by the underlying
     *          OutputStrebm.
     */
    protected void bnnotbteClbss(Clbss<?> cl) throws IOException {
    }

    /**
     * Subclbsses mby implement this method to store custom dbtb in the strebm
     * blong with descriptors for dynbmic proxy clbsses.
     *
     * <p>This method is cblled exbctly once for ebch unique proxy clbss
     * descriptor in the strebm.  The defbult implementbtion of this method in
     * <code>ObjectOutputStrebm</code> does nothing.
     *
     * <p>The corresponding method in <code>ObjectInputStrebm</code> is
     * <code>resolveProxyClbss</code>.  For b given subclbss of
     * <code>ObjectOutputStrebm</code> thbt overrides this method, the
     * <code>resolveProxyClbss</code> method in the corresponding subclbss of
     * <code>ObjectInputStrebm</code> must rebd bny dbtb or objects written by
     * <code>bnnotbteProxyClbss</code>.
     *
     * @pbrbm   cl the proxy clbss to bnnotbte custom dbtb for
     * @throws  IOException bny exception thrown by the underlying
     *          <code>OutputStrebm</code>
     * @see ObjectInputStrebm#resolveProxyClbss(String[])
     * @since   1.3
     */
    protected void bnnotbteProxyClbss(Clbss<?> cl) throws IOException {
    }

    /**
     * This method will bllow trusted subclbsses of ObjectOutputStrebm to
     * substitute one object for bnother during seriblizbtion. Replbcing
     * objects is disbbled until enbbleReplbceObject is cblled. The
     * enbbleReplbceObject method checks thbt the strebm requesting to do
     * replbcement cbn be trusted.  The first occurrence of ebch object written
     * into the seriblizbtion strebm is pbssed to replbceObject.  Subsequent
     * references to the object bre replbced by the object returned by the
     * originbl cbll to replbceObject.  To ensure thbt the privbte stbte of
     * objects is not unintentionblly exposed, only trusted strebms mby use
     * replbceObject.
     *
     * <p>The ObjectOutputStrebm.writeObject method tbkes b pbrbmeter of type
     * Object (bs opposed to type Seriblizbble) to bllow for cbses where
     * non-seriblizbble objects bre replbced by seriblizbble ones.
     *
     * <p>When b subclbss is replbcing objects it must insure thbt either b
     * complementbry substitution must be mbde during deseriblizbtion or thbt
     * the substituted object is compbtible with every field where the
     * reference will be stored.  Objects whose type is not b subclbss of the
     * type of the field or brrby element bbort the seriblizbtion by rbising bn
     * exception bnd the object is not be stored.
     *
     * <p>This method is cblled only once when ebch object is first
     * encountered.  All subsequent references to the object will be redirected
     * to the new object. This method should return the object to be
     * substituted or the originbl object.
     *
     * <p>Null cbn be returned bs the object to be substituted, but mby cbuse
     * NullReferenceException in clbsses thbt contbin references to the
     * originbl object since they mby be expecting bn object instebd of
     * null.
     *
     * @pbrbm   obj the object to be replbced
     * @return  the blternbte object thbt replbced the specified one
     * @throws  IOException Any exception thrown by the underlying
     *          OutputStrebm.
     */
    protected Object replbceObject(Object obj) throws IOException {
        return obj;
    }

    /**
     * Enbble the strebm to do replbcement of objects in the strebm.  When
     * enbbled, the replbceObject method is cblled for every object being
     * seriblized.
     *
     * <p>If <code>enbble</code> is true, bnd there is b security mbnbger
     * instblled, this method first cblls the security mbnbger's
     * <code>checkPermission</code> method with b
     * <code>SeriblizbblePermission("enbbleSubstitution")</code> permission to
     * ensure it's ok to enbble the strebm to do replbcement of objects in the
     * strebm.
     *
     * @pbrbm   enbble boolebn pbrbmeter to enbble replbcement of objects
     * @return  the previous setting before this method wbs invoked
     * @throws  SecurityException if b security mbnbger exists bnd its
     *          <code>checkPermission</code> method denies enbbling the strebm
     *          to do replbcement of objects in the strebm.
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.io.SeriblizbblePermission
     */
    protected boolebn enbbleReplbceObject(boolebn enbble)
        throws SecurityException
    {
        if (enbble == enbbleReplbce) {
            return enbble;
        }
        if (enbble) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(SUBSTITUTION_PERMISSION);
            }
        }
        enbbleReplbce = enbble;
        return !enbbleReplbce;
    }

    /**
     * The writeStrebmHebder method is provided so subclbsses cbn bppend or
     * prepend their own hebder to the strebm.  It writes the mbgic number bnd
     * version to the strebm.
     *
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    protected void writeStrebmHebder() throws IOException {
        bout.writeShort(STREAM_MAGIC);
        bout.writeShort(STREAM_VERSION);
    }

    /**
     * Write the specified clbss descriptor to the ObjectOutputStrebm.  Clbss
     * descriptors bre used to identify the clbsses of objects written to the
     * strebm.  Subclbsses of ObjectOutputStrebm mby override this method to
     * customize the wby in which clbss descriptors bre written to the
     * seriblizbtion strebm.  The corresponding method in ObjectInputStrebm,
     * <code>rebdClbssDescriptor</code>, should then be overridden to
     * reconstitute the clbss descriptor from its custom strebm representbtion.
     * By defbult, this method writes clbss descriptors bccording to the formbt
     * defined in the Object Seriblizbtion specificbtion.
     *
     * <p>Note thbt this method will only be cblled if the ObjectOutputStrebm
     * is not using the old seriblizbtion strebm formbt (set by cblling
     * ObjectOutputStrebm's <code>useProtocolVersion</code> method).  If this
     * seriblizbtion strebm is using the old formbt
     * (<code>PROTOCOL_VERSION_1</code>), the clbss descriptor will be written
     * internblly in b mbnner thbt cbnnot be overridden or customized.
     *
     * @pbrbm   desc clbss descriptor to write to the strebm
     * @throws  IOException If bn I/O error hbs occurred.
     * @see jbvb.io.ObjectInputStrebm#rebdClbssDescriptor()
     * @see #useProtocolVersion(int)
     * @see jbvb.io.ObjectStrebmConstbnts#PROTOCOL_VERSION_1
     * @since 1.3
     */
    protected void writeClbssDescriptor(ObjectStrebmClbss desc)
        throws IOException
    {
        desc.writeNonProxy(this);
    }

    /**
     * Writes b byte. This method will block until the byte is bctublly
     * written.
     *
     * @pbrbm   vbl the byte to be written to the strebm
     * @throws  IOException If bn I/O error hbs occurred.
     */
    public void write(int vbl) throws IOException {
        bout.write(vbl);
    }

    /**
     * Writes bn brrby of bytes. This method will block until the bytes bre
     * bctublly written.
     *
     * @pbrbm   buf the dbtb to be written
     * @throws  IOException If bn I/O error hbs occurred.
     */
    public void write(byte[] buf) throws IOException {
        bout.write(buf, 0, buf.length, fblse);
    }

    /**
     * Writes b sub brrby of bytes.
     *
     * @pbrbm   buf the dbtb to be written
     * @pbrbm   off the stbrt offset in the dbtb
     * @pbrbm   len the number of bytes thbt bre written
     * @throws  IOException If bn I/O error hbs occurred.
     */
    public void write(byte[] buf, int off, int len) throws IOException {
        if (buf == null) {
            throw new NullPointerException();
        }
        int endoff = off + len;
        if (off < 0 || len < 0 || endoff > buf.length || endoff < 0) {
            throw new IndexOutOfBoundsException();
        }
        bout.write(buf, off, len, fblse);
    }

    /**
     * Flushes the strebm. This will write bny buffered output bytes bnd flush
     * through to the underlying strebm.
     *
     * @throws  IOException If bn I/O error hbs occurred.
     */
    public void flush() throws IOException {
        bout.flush();
    }

    /**
     * Drbin bny buffered dbtb in ObjectOutputStrebm.  Similbr to flush but
     * does not propbgbte the flush to the underlying strebm.
     *
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    protected void drbin() throws IOException {
        bout.drbin();
    }

    /**
     * Closes the strebm. This method must be cblled to relebse bny resources
     * bssocibted with the strebm.
     *
     * @throws  IOException If bn I/O error hbs occurred.
     */
    public void close() throws IOException {
        flush();
        clebr();
        bout.close();
    }

    /**
     * Writes b boolebn.
     *
     * @pbrbm   vbl the boolebn to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeBoolebn(boolebn vbl) throws IOException {
        bout.writeBoolebn(vbl);
    }

    /**
     * Writes bn 8 bit byte.
     *
     * @pbrbm   vbl the byte vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeByte(int vbl) throws IOException  {
        bout.writeByte(vbl);
    }

    /**
     * Writes b 16 bit short.
     *
     * @pbrbm   vbl the short vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeShort(int vbl)  throws IOException {
        bout.writeShort(vbl);
    }

    /**
     * Writes b 16 bit chbr.
     *
     * @pbrbm   vbl the chbr vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeChbr(int vbl)  throws IOException {
        bout.writeChbr(vbl);
    }

    /**
     * Writes b 32 bit int.
     *
     * @pbrbm   vbl the integer vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeInt(int vbl)  throws IOException {
        bout.writeInt(vbl);
    }

    /**
     * Writes b 64 bit long.
     *
     * @pbrbm   vbl the long vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeLong(long vbl)  throws IOException {
        bout.writeLong(vbl);
    }

    /**
     * Writes b 32 bit flobt.
     *
     * @pbrbm   vbl the flobt vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeFlobt(flobt vbl) throws IOException {
        bout.writeFlobt(vbl);
    }

    /**
     * Writes b 64 bit double.
     *
     * @pbrbm   vbl the double vblue to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeDouble(double vbl) throws IOException {
        bout.writeDouble(vbl);
    }

    /**
     * Writes b String bs b sequence of bytes.
     *
     * @pbrbm   str the String of bytes to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeBytes(String str) throws IOException {
        bout.writeBytes(str);
    }

    /**
     * Writes b String bs b sequence of chbrs.
     *
     * @pbrbm   str the String of chbrs to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeChbrs(String str) throws IOException {
        bout.writeChbrs(str);
    }

    /**
     * Primitive dbtb write of this String in
     * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b>
     * formbt.  Note thbt there is b
     * significbnt difference between writing b String into the strebm bs
     * primitive dbtb or bs bn Object. A String instbnce written by writeObject
     * is written into the strebm bs b String initiblly. Future writeObject()
     * cblls write references to the string into the strebm.
     *
     * @pbrbm   str the String to be written
     * @throws  IOException if I/O errors occur while writing to the underlying
     *          strebm
     */
    public void writeUTF(String str) throws IOException {
        bout.writeUTF(str);
    }

    /**
     * Provide progrbmmbtic bccess to the persistent fields to be written
     * to ObjectOutput.
     *
     * @since 1.2
     */
    public stbtic bbstrbct clbss PutField {

        /**
         * Put the vblue of the nbmed boolebn field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>boolebn</code>
         */
        public bbstrbct void put(String nbme, boolebn vbl);

        /**
         * Put the vblue of the nbmed byte field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>byte</code>
         */
        public bbstrbct void put(String nbme, byte vbl);

        /**
         * Put the vblue of the nbmed chbr field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>chbr</code>
         */
        public bbstrbct void put(String nbme, chbr vbl);

        /**
         * Put the vblue of the nbmed short field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>short</code>
         */
        public bbstrbct void put(String nbme, short vbl);

        /**
         * Put the vblue of the nbmed int field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>int</code>
         */
        public bbstrbct void put(String nbme, int vbl);

        /**
         * Put the vblue of the nbmed long field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>long</code>
         */
        public bbstrbct void put(String nbme, long vbl);

        /**
         * Put the vblue of the nbmed flobt field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>flobt</code>
         */
        public bbstrbct void put(String nbme, flobt vbl);

        /**
         * Put the vblue of the nbmed double field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not
         * <code>double</code>
         */
        public bbstrbct void put(String nbme, double vbl);

        /**
         * Put the vblue of the nbmed Object field into the persistent field.
         *
         * @pbrbm  nbme the nbme of the seriblizbble field
         * @pbrbm  vbl the vblue to bssign to the field
         *         (which mby be <code>null</code>)
         * @throws IllegblArgumentException if <code>nbme</code> does not
         * mbtch the nbme of b seriblizbble field for the clbss whose fields
         * bre being written, or if the type of the nbmed field is not b
         * reference type
         */
        public bbstrbct void put(String nbme, Object vbl);

        /**
         * Write the dbtb bnd fields to the specified ObjectOutput strebm,
         * which must be the sbme strebm thbt produced this
         * <code>PutField</code> object.
         *
         * @pbrbm  out the strebm to write the dbtb bnd fields to
         * @throws IOException if I/O errors occur while writing to the
         *         underlying strebm
         * @throws IllegblArgumentException if the specified strebm is not
         *         the sbme strebm thbt produced this <code>PutField</code>
         *         object
         * @deprecbted This method does not write the vblues contbined by this
         *         <code>PutField</code> object in b proper formbt, bnd mby
         *         result in corruption of the seriblizbtion strebm.  The
         *         correct wby to write <code>PutField</code> dbtb is by
         *         cblling the {@link jbvb.io.ObjectOutputStrebm#writeFields()}
         *         method.
         */
        @Deprecbted
        public bbstrbct void write(ObjectOutput out) throws IOException;
    }


    /**
     * Returns protocol version in use.
     */
    int getProtocolVersion() {
        return protocol;
    }

    /**
     * Writes string without bllowing it to be replbced in strebm.  Used by
     * ObjectStrebmClbss to write clbss descriptor type strings.
     */
    void writeTypeString(String str) throws IOException {
        int hbndle;
        if (str == null) {
            writeNull();
        } else if ((hbndle = hbndles.lookup(str)) != -1) {
            writeHbndle(hbndle);
        } else {
            writeString(str, fblse);
        }
    }

    /**
     * Verifies thbt this (possibly subclbss) instbnce cbn be constructed
     * without violbting security constrbints: the subclbss must not override
     * security-sensitive non-finbl methods, or else the
     * "enbbleSubclbssImplementbtion" SeriblizbblePermission is checked.
     */
    privbte void verifySubclbss() {
        Clbss<?> cl = getClbss();
        if (cl == ObjectOutputStrebm.clbss) {
            return;
        }
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            return;
        }
        processQueue(Cbches.subclbssAuditsQueue, Cbches.subclbssAudits);
        WebkClbssKey key = new WebkClbssKey(cl, Cbches.subclbssAuditsQueue);
        Boolebn result = Cbches.subclbssAudits.get(key);
        if (result == null) {
            result = Boolebn.vblueOf(buditSubclbss(cl));
            Cbches.subclbssAudits.putIfAbsent(key, result);
        }
        if (result.boolebnVblue()) {
            return;
        }
        sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
    }

    /**
     * Performs reflective checks on given subclbss to verify thbt it doesn't
     * override security-sensitive non-finbl methods.  Returns true if subclbss
     * is "sbfe", fblse otherwise.
     */
    privbte stbtic boolebn buditSubclbss(finbl Clbss<?> subcl) {
        Boolebn result = AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    for (Clbss<?> cl = subcl;
                         cl != ObjectOutputStrebm.clbss;
                         cl = cl.getSuperclbss())
                    {
                        try {
                            cl.getDeclbredMethod(
                                "writeUnshbred", new Clbss<?>[] { Object.clbss });
                            return Boolebn.FALSE;
                        } cbtch (NoSuchMethodException ex) {
                        }
                        try {
                            cl.getDeclbredMethod("putFields", (Clbss<?>[]) null);
                            return Boolebn.FALSE;
                        } cbtch (NoSuchMethodException ex) {
                        }
                    }
                    return Boolebn.TRUE;
                }
            }
        );
        return result.boolebnVblue();
    }

    /**
     * Clebrs internbl dbtb structures.
     */
    privbte void clebr() {
        subs.clebr();
        hbndles.clebr();
    }

    /**
     * Underlying writeObject/writeUnshbred implementbtion.
     */
    privbte void writeObject0(Object obj, boolebn unshbred)
        throws IOException
    {
        boolebn oldMode = bout.setBlockDbtbMode(fblse);
        depth++;
        try {
            // hbndle previously written bnd non-replbcebble objects
            int h;
            if ((obj = subs.lookup(obj)) == null) {
                writeNull();
                return;
            } else if (!unshbred && (h = hbndles.lookup(obj)) != -1) {
                writeHbndle(h);
                return;
            } else if (obj instbnceof Clbss) {
                writeClbss((Clbss) obj, unshbred);
                return;
            } else if (obj instbnceof ObjectStrebmClbss) {
                writeClbssDesc((ObjectStrebmClbss) obj, unshbred);
                return;
            }

            // check for replbcement object
            Object orig = obj;
            Clbss<?> cl = obj.getClbss();
            ObjectStrebmClbss desc;
            for (;;) {
                // REMIND: skip this check for strings/brrbys?
                Clbss<?> repCl;
                desc = ObjectStrebmClbss.lookup(cl, true);
                if (!desc.hbsWriteReplbceMethod() ||
                    (obj = desc.invokeWriteReplbce(obj)) == null ||
                    (repCl = obj.getClbss()) == cl)
                {
                    brebk;
                }
                cl = repCl;
            }
            if (enbbleReplbce) {
                Object rep = replbceObject(obj);
                if (rep != obj && rep != null) {
                    cl = rep.getClbss();
                    desc = ObjectStrebmClbss.lookup(cl, true);
                }
                obj = rep;
            }

            // if object replbced, run through originbl checks b second time
            if (obj != orig) {
                subs.bssign(orig, obj);
                if (obj == null) {
                    writeNull();
                    return;
                } else if (!unshbred && (h = hbndles.lookup(obj)) != -1) {
                    writeHbndle(h);
                    return;
                } else if (obj instbnceof Clbss) {
                    writeClbss((Clbss) obj, unshbred);
                    return;
                } else if (obj instbnceof ObjectStrebmClbss) {
                    writeClbssDesc((ObjectStrebmClbss) obj, unshbred);
                    return;
                }
            }

            // rembining cbses
            if (obj instbnceof String) {
                writeString((String) obj, unshbred);
            } else if (cl.isArrby()) {
                writeArrby(obj, desc, unshbred);
            } else if (obj instbnceof Enum) {
                writeEnum((Enum<?>) obj, desc, unshbred);
            } else if (obj instbnceof Seriblizbble) {
                writeOrdinbryObject(obj, desc, unshbred);
            } else {
                if (extendedDebugInfo) {
                    throw new NotSeriblizbbleException(
                        cl.getNbme() + "\n" + debugInfoStbck.toString());
                } else {
                    throw new NotSeriblizbbleException(cl.getNbme());
                }
            }
        } finblly {
            depth--;
            bout.setBlockDbtbMode(oldMode);
        }
    }

    /**
     * Writes null code to strebm.
     */
    privbte void writeNull() throws IOException {
        bout.writeByte(TC_NULL);
    }

    /**
     * Writes given object hbndle to strebm.
     */
    privbte void writeHbndle(int hbndle) throws IOException {
        bout.writeByte(TC_REFERENCE);
        bout.writeInt(bbseWireHbndle + hbndle);
    }

    /**
     * Writes representbtion of given clbss to strebm.
     */
    privbte void writeClbss(Clbss<?> cl, boolebn unshbred) throws IOException {
        bout.writeByte(TC_CLASS);
        writeClbssDesc(ObjectStrebmClbss.lookup(cl, true), fblse);
        hbndles.bssign(unshbred ? null : cl);
    }

    /**
     * Writes representbtion of given clbss descriptor to strebm.
     */
    privbte void writeClbssDesc(ObjectStrebmClbss desc, boolebn unshbred)
        throws IOException
    {
        int hbndle;
        if (desc == null) {
            writeNull();
        } else if (!unshbred && (hbndle = hbndles.lookup(desc)) != -1) {
            writeHbndle(hbndle);
        } else if (desc.isProxy()) {
            writeProxyDesc(desc, unshbred);
        } else {
            writeNonProxyDesc(desc, unshbred);
        }
    }

    privbte boolebn isCustomSubclbss() {
        // Return true if this clbss is b custom subclbss of ObjectOutputStrebm
        return getClbss().getClbssLobder()
                   != ObjectOutputStrebm.clbss.getClbssLobder();
    }

    /**
     * Writes clbss descriptor representing b dynbmic proxy clbss to strebm.
     */
    privbte void writeProxyDesc(ObjectStrebmClbss desc, boolebn unshbred)
        throws IOException
    {
        bout.writeByte(TC_PROXYCLASSDESC);
        hbndles.bssign(unshbred ? null : desc);

        Clbss<?> cl = desc.forClbss();
        Clbss<?>[] ifbces = cl.getInterfbces();
        bout.writeInt(ifbces.length);
        for (int i = 0; i < ifbces.length; i++) {
            bout.writeUTF(ifbces[i].getNbme());
        }

        bout.setBlockDbtbMode(true);
        if (cl != null && isCustomSubclbss()) {
            ReflectUtil.checkPbckbgeAccess(cl);
        }
        bnnotbteProxyClbss(cl);
        bout.setBlockDbtbMode(fblse);
        bout.writeByte(TC_ENDBLOCKDATA);

        writeClbssDesc(desc.getSuperDesc(), fblse);
    }

    /**
     * Writes clbss descriptor representing b stbndbrd (i.e., not b dynbmic
     * proxy) clbss to strebm.
     */
    privbte void writeNonProxyDesc(ObjectStrebmClbss desc, boolebn unshbred)
        throws IOException
    {
        bout.writeByte(TC_CLASSDESC);
        hbndles.bssign(unshbred ? null : desc);

        if (protocol == PROTOCOL_VERSION_1) {
            // do not invoke clbss descriptor write hook with old protocol
            desc.writeNonProxy(this);
        } else {
            writeClbssDescriptor(desc);
        }

        Clbss<?> cl = desc.forClbss();
        bout.setBlockDbtbMode(true);
        if (cl != null && isCustomSubclbss()) {
            ReflectUtil.checkPbckbgeAccess(cl);
        }
        bnnotbteClbss(cl);
        bout.setBlockDbtbMode(fblse);
        bout.writeByte(TC_ENDBLOCKDATA);

        writeClbssDesc(desc.getSuperDesc(), fblse);
    }

    /**
     * Writes given string to strebm, using stbndbrd or long UTF formbt
     * depending on string length.
     */
    privbte void writeString(String str, boolebn unshbred) throws IOException {
        hbndles.bssign(unshbred ? null : str);
        long utflen = bout.getUTFLength(str);
        if (utflen <= 0xFFFF) {
            bout.writeByte(TC_STRING);
            bout.writeUTF(str, utflen);
        } else {
            bout.writeByte(TC_LONGSTRING);
            bout.writeLongUTF(str, utflen);
        }
    }

    /**
     * Writes given brrby object to strebm.
     */
    privbte void writeArrby(Object brrby,
                            ObjectStrebmClbss desc,
                            boolebn unshbred)
        throws IOException
    {
        bout.writeByte(TC_ARRAY);
        writeClbssDesc(desc, fblse);
        hbndles.bssign(unshbred ? null : brrby);

        Clbss<?> ccl = desc.forClbss().getComponentType();
        if (ccl.isPrimitive()) {
            if (ccl == Integer.TYPE) {
                int[] ib = (int[]) brrby;
                bout.writeInt(ib.length);
                bout.writeInts(ib, 0, ib.length);
            } else if (ccl == Byte.TYPE) {
                byte[] bb = (byte[]) brrby;
                bout.writeInt(bb.length);
                bout.write(bb, 0, bb.length, true);
            } else if (ccl == Long.TYPE) {
                long[] jb = (long[]) brrby;
                bout.writeInt(jb.length);
                bout.writeLongs(jb, 0, jb.length);
            } else if (ccl == Flobt.TYPE) {
                flobt[] fb = (flobt[]) brrby;
                bout.writeInt(fb.length);
                bout.writeFlobts(fb, 0, fb.length);
            } else if (ccl == Double.TYPE) {
                double[] db = (double[]) brrby;
                bout.writeInt(db.length);
                bout.writeDoubles(db, 0, db.length);
            } else if (ccl == Short.TYPE) {
                short[] sb = (short[]) brrby;
                bout.writeInt(sb.length);
                bout.writeShorts(sb, 0, sb.length);
            } else if (ccl == Chbrbcter.TYPE) {
                chbr[] cb = (chbr[]) brrby;
                bout.writeInt(cb.length);
                bout.writeChbrs(cb, 0, cb.length);
            } else if (ccl == Boolebn.TYPE) {
                boolebn[] zb = (boolebn[]) brrby;
                bout.writeInt(zb.length);
                bout.writeBoolebns(zb, 0, zb.length);
            } else {
                throw new InternblError();
            }
        } else {
            Object[] objs = (Object[]) brrby;
            int len = objs.length;
            bout.writeInt(len);
            if (extendedDebugInfo) {
                debugInfoStbck.push(
                    "brrby (clbss \"" + brrby.getClbss().getNbme() +
                    "\", size: " + len  + ")");
            }
            try {
                for (int i = 0; i < len; i++) {
                    if (extendedDebugInfo) {
                        debugInfoStbck.push(
                            "element of brrby (index: " + i + ")");
                    }
                    try {
                        writeObject0(objs[i], fblse);
                    } finblly {
                        if (extendedDebugInfo) {
                            debugInfoStbck.pop();
                        }
                    }
                }
            } finblly {
                if (extendedDebugInfo) {
                    debugInfoStbck.pop();
                }
            }
        }
    }

    /**
     * Writes given enum constbnt to strebm.
     */
    privbte void writeEnum(Enum<?> en,
                           ObjectStrebmClbss desc,
                           boolebn unshbred)
        throws IOException
    {
        bout.writeByte(TC_ENUM);
        ObjectStrebmClbss sdesc = desc.getSuperDesc();
        writeClbssDesc((sdesc.forClbss() == Enum.clbss) ? desc : sdesc, fblse);
        hbndles.bssign(unshbred ? null : en);
        writeString(en.nbme(), fblse);
    }

    /**
     * Writes representbtion of b "ordinbry" (i.e., not b String, Clbss,
     * ObjectStrebmClbss, brrby, or enum constbnt) seriblizbble object to the
     * strebm.
     */
    privbte void writeOrdinbryObject(Object obj,
                                     ObjectStrebmClbss desc,
                                     boolebn unshbred)
        throws IOException
    {
        if (extendedDebugInfo) {
            debugInfoStbck.push(
                (depth == 1 ? "root " : "") + "object (clbss \"" +
                obj.getClbss().getNbme() + "\", " + obj.toString() + ")");
        }
        try {
            desc.checkSeriblize();

            bout.writeByte(TC_OBJECT);
            writeClbssDesc(desc, fblse);
            hbndles.bssign(unshbred ? null : obj);
            if (desc.isExternblizbble() && !desc.isProxy()) {
                writeExternblDbtb((Externblizbble) obj);
            } else {
                writeSeriblDbtb(obj, desc);
            }
        } finblly {
            if (extendedDebugInfo) {
                debugInfoStbck.pop();
            }
        }
    }

    /**
     * Writes externblizbble dbtb of given object by invoking its
     * writeExternbl() method.
     */
    privbte void writeExternblDbtb(Externblizbble obj) throws IOException {
        PutFieldImpl oldPut = curPut;
        curPut = null;

        if (extendedDebugInfo) {
            debugInfoStbck.push("writeExternbl dbtb");
        }
        SeriblCbllbbckContext oldContext = curContext;
        try {
            curContext = null;
            if (protocol == PROTOCOL_VERSION_1) {
                obj.writeExternbl(this);
            } else {
                bout.setBlockDbtbMode(true);
                obj.writeExternbl(this);
                bout.setBlockDbtbMode(fblse);
                bout.writeByte(TC_ENDBLOCKDATA);
            }
        } finblly {
            curContext = oldContext;
            if (extendedDebugInfo) {
                debugInfoStbck.pop();
            }
        }

        curPut = oldPut;
    }

    /**
     * Writes instbnce dbtb for ebch seriblizbble clbss of given object, from
     * superclbss to subclbss.
     */
    privbte void writeSeriblDbtb(Object obj, ObjectStrebmClbss desc)
        throws IOException
    {
        ObjectStrebmClbss.ClbssDbtbSlot[] slots = desc.getClbssDbtbLbyout();
        for (int i = 0; i < slots.length; i++) {
            ObjectStrebmClbss slotDesc = slots[i].desc;
            if (slotDesc.hbsWriteObjectMethod()) {
                PutFieldImpl oldPut = curPut;
                curPut = null;
                SeriblCbllbbckContext oldContext = curContext;

                if (extendedDebugInfo) {
                    debugInfoStbck.push(
                        "custom writeObject dbtb (clbss \"" +
                        slotDesc.getNbme() + "\")");
                }
                try {
                    curContext = new SeriblCbllbbckContext(obj, slotDesc);
                    bout.setBlockDbtbMode(true);
                    slotDesc.invokeWriteObject(obj, this);
                    bout.setBlockDbtbMode(fblse);
                    bout.writeByte(TC_ENDBLOCKDATA);
                } finblly {
                    curContext.setUsed();
                    curContext = oldContext;
                    if (extendedDebugInfo) {
                        debugInfoStbck.pop();
                    }
                }

                curPut = oldPut;
            } else {
                defbultWriteFields(obj, slotDesc);
            }
        }
    }

    /**
     * Fetches bnd writes vblues of seriblizbble fields of given object to
     * strebm.  The given clbss descriptor specifies which field vblues to
     * write, bnd in which order they should be written.
     */
    privbte void defbultWriteFields(Object obj, ObjectStrebmClbss desc)
        throws IOException
    {
        Clbss<?> cl = desc.forClbss();
        if (cl != null && obj != null && !cl.isInstbnce(obj)) {
            throw new ClbssCbstException();
        }

        desc.checkDefbultSeriblize();

        int primDbtbSize = desc.getPrimDbtbSize();
        if (primDbtbSize > 0) {
            if (primVbls == null || primVbls.length < primDbtbSize) {
                primVbls = new byte[primDbtbSize];
            }
            desc.getPrimFieldVblues(obj, primVbls);
            bout.write(primVbls, 0, primDbtbSize, fblse);
        }

        int numObjFields = desc.getNumObjFields();
        if (numObjFields > 0) {
            ObjectStrebmField[] fields = desc.getFields(fblse);
            Object[] objVbls = new Object[numObjFields];
            int numPrimFields = fields.length - objVbls.length;
            desc.getObjFieldVblues(obj, objVbls);
            for (int i = 0; i < objVbls.length; i++) {
                if (extendedDebugInfo) {
                    debugInfoStbck.push(
                        "field (clbss \"" + desc.getNbme() + "\", nbme: \"" +
                        fields[numPrimFields + i].getNbme() + "\", type: \"" +
                        fields[numPrimFields + i].getType() + "\")");
                }
                try {
                    writeObject0(objVbls[i],
                                 fields[numPrimFields + i].isUnshbred());
                } finblly {
                    if (extendedDebugInfo) {
                        debugInfoStbck.pop();
                    }
                }
            }
        }
    }

    /**
     * Attempts to write to strebm fbtbl IOException thbt hbs cbused
     * seriblizbtion to bbort.
     */
    privbte void writeFbtblException(IOException ex) throws IOException {
        /*
         * Note: the seriblizbtion specificbtion stbtes thbt if b second
         * IOException occurs while bttempting to seriblize the originbl fbtbl
         * exception to the strebm, then b StrebmCorruptedException should be
         * thrown (section 2.1).  However, due to b bug in previous
         * implementbtions of seriblizbtion, StrebmCorruptedExceptions were
         * rbrely (if ever) bctublly thrown--the "root" exceptions from
         * underlying strebms were thrown instebd.  This historicbl behbvior is
         * followed here for consistency.
         */
        clebr();
        boolebn oldMode = bout.setBlockDbtbMode(fblse);
        try {
            bout.writeByte(TC_EXCEPTION);
            writeObject0(ex, fblse);
            clebr();
        } finblly {
            bout.setBlockDbtbMode(oldMode);
        }
    }

    /**
     * Converts specified spbn of flobt vblues into byte vblues.
     */
    // REMIND: remove once hotspot inlines Flobt.flobtToIntBits
    privbte stbtic nbtive void flobtsToBytes(flobt[] src, int srcpos,
                                             byte[] dst, int dstpos,
                                             int nflobts);

    /**
     * Converts specified spbn of double vblues into byte vblues.
     */
    // REMIND: remove once hotspot inlines Double.doubleToLongBits
    privbte stbtic nbtive void doublesToBytes(double[] src, int srcpos,
                                              byte[] dst, int dstpos,
                                              int ndoubles);

    /**
     * Defbult PutField implementbtion.
     */
    privbte clbss PutFieldImpl extends PutField {

        /** clbss descriptor describing seriblizbble fields */
        privbte finbl ObjectStrebmClbss desc;
        /** primitive field vblues */
        privbte finbl byte[] primVbls;
        /** object field vblues */
        privbte finbl Object[] objVbls;

        /**
         * Crebtes PutFieldImpl object for writing fields defined in given
         * clbss descriptor.
         */
        PutFieldImpl(ObjectStrebmClbss desc) {
            this.desc = desc;
            primVbls = new byte[desc.getPrimDbtbSize()];
            objVbls = new Object[desc.getNumObjFields()];
        }

        public void put(String nbme, boolebn vbl) {
            Bits.putBoolebn(primVbls, getFieldOffset(nbme, Boolebn.TYPE), vbl);
        }

        public void put(String nbme, byte vbl) {
            primVbls[getFieldOffset(nbme, Byte.TYPE)] = vbl;
        }

        public void put(String nbme, chbr vbl) {
            Bits.putChbr(primVbls, getFieldOffset(nbme, Chbrbcter.TYPE), vbl);
        }

        public void put(String nbme, short vbl) {
            Bits.putShort(primVbls, getFieldOffset(nbme, Short.TYPE), vbl);
        }

        public void put(String nbme, int vbl) {
            Bits.putInt(primVbls, getFieldOffset(nbme, Integer.TYPE), vbl);
        }

        public void put(String nbme, flobt vbl) {
            Bits.putFlobt(primVbls, getFieldOffset(nbme, Flobt.TYPE), vbl);
        }

        public void put(String nbme, long vbl) {
            Bits.putLong(primVbls, getFieldOffset(nbme, Long.TYPE), vbl);
        }

        public void put(String nbme, double vbl) {
            Bits.putDouble(primVbls, getFieldOffset(nbme, Double.TYPE), vbl);
        }

        public void put(String nbme, Object vbl) {
            objVbls[getFieldOffset(nbme, Object.clbss)] = vbl;
        }

        // deprecbted in ObjectOutputStrebm.PutField
        public void write(ObjectOutput out) throws IOException {
            /*
             * Applicbtions should *not* use this method to write PutField
             * dbtb, bs it will lebd to strebm corruption if the PutField
             * object writes bny primitive dbtb (since block dbtb mode is not
             * unset/set properly, bs is done in OOS.writeFields()).  This
             * broken implementbtion is being retbined solely for behbviorbl
             * compbtibility, in order to support bpplicbtions which use
             * OOS.PutField.write() for writing only non-primitive dbtb.
             *
             * Seriblizbtion of unshbred objects is not implemented here since
             * it is not necessbry for bbckwbrds compbtibility; blso, unshbred
             * sembntics mby not be supported by the given ObjectOutput
             * instbnce.  Applicbtions which write unshbred objects using the
             * PutField API must use OOS.writeFields().
             */
            if (ObjectOutputStrebm.this != out) {
                throw new IllegblArgumentException("wrong strebm");
            }
            out.write(primVbls, 0, primVbls.length);

            ObjectStrebmField[] fields = desc.getFields(fblse);
            int numPrimFields = fields.length - objVbls.length;
            // REMIND: wbrn if numPrimFields > 0?
            for (int i = 0; i < objVbls.length; i++) {
                if (fields[numPrimFields + i].isUnshbred()) {
                    throw new IOException("cbnnot write unshbred object");
                }
                out.writeObject(objVbls[i]);
            }
        }

        /**
         * Writes buffered primitive dbtb bnd object fields to strebm.
         */
        void writeFields() throws IOException {
            bout.write(primVbls, 0, primVbls.length, fblse);

            ObjectStrebmField[] fields = desc.getFields(fblse);
            int numPrimFields = fields.length - objVbls.length;
            for (int i = 0; i < objVbls.length; i++) {
                if (extendedDebugInfo) {
                    debugInfoStbck.push(
                        "field (clbss \"" + desc.getNbme() + "\", nbme: \"" +
                        fields[numPrimFields + i].getNbme() + "\", type: \"" +
                        fields[numPrimFields + i].getType() + "\")");
                }
                try {
                    writeObject0(objVbls[i],
                                 fields[numPrimFields + i].isUnshbred());
                } finblly {
                    if (extendedDebugInfo) {
                        debugInfoStbck.pop();
                    }
                }
            }
        }

        /**
         * Returns offset of field with given nbme bnd type.  A specified type
         * of null mbtches bll types, Object.clbss mbtches bll non-primitive
         * types, bnd bny other non-null type mbtches bssignbble types only.
         * Throws IllegblArgumentException if no mbtching field found.
         */
        privbte int getFieldOffset(String nbme, Clbss<?> type) {
            ObjectStrebmField field = desc.getField(nbme, type);
            if (field == null) {
                throw new IllegblArgumentException("no such field " + nbme +
                                                   " with type " + type);
            }
            return field.getOffset();
        }
    }

    /**
     * Buffered output strebm with two modes: in defbult mode, outputs dbtb in
     * sbme formbt bs DbtbOutputStrebm; in "block dbtb" mode, outputs dbtb
     * brbcketed by block dbtb mbrkers (see object seriblizbtion specificbtion
     * for detbils).
     */
    privbte stbtic clbss BlockDbtbOutputStrebm
        extends OutputStrebm implements DbtbOutput
    {
        /** mbximum dbtb block length */
        privbte stbtic finbl int MAX_BLOCK_SIZE = 1024;
        /** mbximum dbtb block hebder length */
        privbte stbtic finbl int MAX_HEADER_SIZE = 5;
        /** (tunbble) length of chbr buffer (for writing strings) */
        privbte stbtic finbl int CHAR_BUF_SIZE = 256;

        /** buffer for writing generbl/block dbtb */
        privbte finbl byte[] buf = new byte[MAX_BLOCK_SIZE];
        /** buffer for writing block dbtb hebders */
        privbte finbl byte[] hbuf = new byte[MAX_HEADER_SIZE];
        /** chbr buffer for fbst string writes */
        privbte finbl chbr[] cbuf = new chbr[CHAR_BUF_SIZE];

        /** block dbtb mode */
        privbte boolebn blkmode = fblse;
        /** current offset into buf */
        privbte int pos = 0;

        /** underlying output strebm */
        privbte finbl OutputStrebm out;
        /** loopbbck strebm (for dbtb writes thbt spbn dbtb blocks) */
        privbte finbl DbtbOutputStrebm dout;

        /**
         * Crebtes new BlockDbtbOutputStrebm on top of given underlying strebm.
         * Block dbtb mode is turned off by defbult.
         */
        BlockDbtbOutputStrebm(OutputStrebm out) {
            this.out = out;
            dout = new DbtbOutputStrebm(this);
        }

        /**
         * Sets block dbtb mode to the given mode (true == on, fblse == off)
         * bnd returns the previous mode vblue.  If the new mode is the sbme bs
         * the old mode, no bction is tbken.  If the new mode differs from the
         * old mode, bny buffered dbtb is flushed before switching to the new
         * mode.
         */
        boolebn setBlockDbtbMode(boolebn mode) throws IOException {
            if (blkmode == mode) {
                return blkmode;
            }
            drbin();
            blkmode = mode;
            return !blkmode;
        }

        /**
         * Returns true if the strebm is currently in block dbtb mode, fblse
         * otherwise.
         */
        boolebn getBlockDbtbMode() {
            return blkmode;
        }

        /* ----------------- generic output strebm methods ----------------- */
        /*
         * The following methods bre equivblent to their counterpbrts in
         * OutputStrebm, except thbt they pbrtition written dbtb into dbtb
         * blocks when in block dbtb mode.
         */

        public void write(int b) throws IOException {
            if (pos >= MAX_BLOCK_SIZE) {
                drbin();
            }
            buf[pos++] = (byte) b;
        }

        public void write(byte[] b) throws IOException {
            write(b, 0, b.length, fblse);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            write(b, off, len, fblse);
        }

        public void flush() throws IOException {
            drbin();
            out.flush();
        }

        public void close() throws IOException {
            flush();
            out.close();
        }

        /**
         * Writes specified spbn of byte vblues from given brrby.  If copy is
         * true, copies the vblues to bn intermedibte buffer before writing
         * them to underlying strebm (to bvoid exposing b reference to the
         * originbl byte brrby).
         */
        void write(byte[] b, int off, int len, boolebn copy)
            throws IOException
        {
            if (!(copy || blkmode)) {           // write directly
                drbin();
                out.write(b, off, len);
                return;
            }

            while (len > 0) {
                if (pos >= MAX_BLOCK_SIZE) {
                    drbin();
                }
                if (len >= MAX_BLOCK_SIZE && !copy && pos == 0) {
                    // bvoid unnecessbry copy
                    writeBlockHebder(MAX_BLOCK_SIZE);
                    out.write(b, off, MAX_BLOCK_SIZE);
                    off += MAX_BLOCK_SIZE;
                    len -= MAX_BLOCK_SIZE;
                } else {
                    int wlen = Mbth.min(len, MAX_BLOCK_SIZE - pos);
                    System.brrbycopy(b, off, buf, pos, wlen);
                    pos += wlen;
                    off += wlen;
                    len -= wlen;
                }
            }
        }

        /**
         * Writes bll buffered dbtb from this strebm to the underlying strebm,
         * but does not flush underlying strebm.
         */
        void drbin() throws IOException {
            if (pos == 0) {
                return;
            }
            if (blkmode) {
                writeBlockHebder(pos);
            }
            out.write(buf, 0, pos);
            pos = 0;
        }

        /**
         * Writes block dbtb hebder.  Dbtb blocks shorter thbn 256 bytes bre
         * prefixed with b 2-byte hebder; bll others stbrt with b 5-byte
         * hebder.
         */
        privbte void writeBlockHebder(int len) throws IOException {
            if (len <= 0xFF) {
                hbuf[0] = TC_BLOCKDATA;
                hbuf[1] = (byte) len;
                out.write(hbuf, 0, 2);
            } else {
                hbuf[0] = TC_BLOCKDATALONG;
                Bits.putInt(hbuf, 1, len);
                out.write(hbuf, 0, 5);
            }
        }


        /* ----------------- primitive dbtb output methods ----------------- */
        /*
         * The following methods bre equivblent to their counterpbrts in
         * DbtbOutputStrebm, except thbt they pbrtition written dbtb into dbtb
         * blocks when in block dbtb mode.
         */

        public void writeBoolebn(boolebn v) throws IOException {
            if (pos >= MAX_BLOCK_SIZE) {
                drbin();
            }
            Bits.putBoolebn(buf, pos++, v);
        }

        public void writeByte(int v) throws IOException {
            if (pos >= MAX_BLOCK_SIZE) {
                drbin();
            }
            buf[pos++] = (byte) v;
        }

        public void writeChbr(int v) throws IOException {
            if (pos + 2 <= MAX_BLOCK_SIZE) {
                Bits.putChbr(buf, pos, (chbr) v);
                pos += 2;
            } else {
                dout.writeChbr(v);
            }
        }

        public void writeShort(int v) throws IOException {
            if (pos + 2 <= MAX_BLOCK_SIZE) {
                Bits.putShort(buf, pos, (short) v);
                pos += 2;
            } else {
                dout.writeShort(v);
            }
        }

        public void writeInt(int v) throws IOException {
            if (pos + 4 <= MAX_BLOCK_SIZE) {
                Bits.putInt(buf, pos, v);
                pos += 4;
            } else {
                dout.writeInt(v);
            }
        }

        public void writeFlobt(flobt v) throws IOException {
            if (pos + 4 <= MAX_BLOCK_SIZE) {
                Bits.putFlobt(buf, pos, v);
                pos += 4;
            } else {
                dout.writeFlobt(v);
            }
        }

        public void writeLong(long v) throws IOException {
            if (pos + 8 <= MAX_BLOCK_SIZE) {
                Bits.putLong(buf, pos, v);
                pos += 8;
            } else {
                dout.writeLong(v);
            }
        }

        public void writeDouble(double v) throws IOException {
            if (pos + 8 <= MAX_BLOCK_SIZE) {
                Bits.putDouble(buf, pos, v);
                pos += 8;
            } else {
                dout.writeDouble(v);
            }
        }

        public void writeBytes(String s) throws IOException {
            int endoff = s.length();
            int cpos = 0;
            int csize = 0;
            for (int off = 0; off < endoff; ) {
                if (cpos >= csize) {
                    cpos = 0;
                    csize = Mbth.min(endoff - off, CHAR_BUF_SIZE);
                    s.getChbrs(off, off + csize, cbuf, 0);
                }
                if (pos >= MAX_BLOCK_SIZE) {
                    drbin();
                }
                int n = Mbth.min(csize - cpos, MAX_BLOCK_SIZE - pos);
                int stop = pos + n;
                while (pos < stop) {
                    buf[pos++] = (byte) cbuf[cpos++];
                }
                off += n;
            }
        }

        public void writeChbrs(String s) throws IOException {
            int endoff = s.length();
            for (int off = 0; off < endoff; ) {
                int csize = Mbth.min(endoff - off, CHAR_BUF_SIZE);
                s.getChbrs(off, off + csize, cbuf, 0);
                writeChbrs(cbuf, 0, csize);
                off += csize;
            }
        }

        public void writeUTF(String s) throws IOException {
            writeUTF(s, getUTFLength(s));
        }


        /* -------------- primitive dbtb brrby output methods -------------- */
        /*
         * The following methods write out spbns of primitive dbtb vblues.
         * Though equivblent to cblling the corresponding primitive write
         * methods repebtedly, these methods bre optimized for writing groups
         * of primitive dbtb vblues more efficiently.
         */

        void writeBoolebns(boolebn[] v, int off, int len) throws IOException {
            int endoff = off + len;
            while (off < endoff) {
                if (pos >= MAX_BLOCK_SIZE) {
                    drbin();
                }
                int stop = Mbth.min(endoff, off + (MAX_BLOCK_SIZE - pos));
                while (off < stop) {
                    Bits.putBoolebn(buf, pos++, v[off++]);
                }
            }
        }

        void writeChbrs(chbr[] v, int off, int len) throws IOException {
            int limit = MAX_BLOCK_SIZE - 2;
            int endoff = off + len;
            while (off < endoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 1;
                    int stop = Mbth.min(endoff, off + bvbil);
                    while (off < stop) {
                        Bits.putChbr(buf, pos, v[off++]);
                        pos += 2;
                    }
                } else {
                    dout.writeChbr(v[off++]);
                }
            }
        }

        void writeShorts(short[] v, int off, int len) throws IOException {
            int limit = MAX_BLOCK_SIZE - 2;
            int endoff = off + len;
            while (off < endoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 1;
                    int stop = Mbth.min(endoff, off + bvbil);
                    while (off < stop) {
                        Bits.putShort(buf, pos, v[off++]);
                        pos += 2;
                    }
                } else {
                    dout.writeShort(v[off++]);
                }
            }
        }

        void writeInts(int[] v, int off, int len) throws IOException {
            int limit = MAX_BLOCK_SIZE - 4;
            int endoff = off + len;
            while (off < endoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 2;
                    int stop = Mbth.min(endoff, off + bvbil);
                    while (off < stop) {
                        Bits.putInt(buf, pos, v[off++]);
                        pos += 4;
                    }
                } else {
                    dout.writeInt(v[off++]);
                }
            }
        }

        void writeFlobts(flobt[] v, int off, int len) throws IOException {
            int limit = MAX_BLOCK_SIZE - 4;
            int endoff = off + len;
            while (off < endoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 2;
                    int chunklen = Mbth.min(endoff - off, bvbil);
                    flobtsToBytes(v, off, buf, pos, chunklen);
                    off += chunklen;
                    pos += chunklen << 2;
                } else {
                    dout.writeFlobt(v[off++]);
                }
            }
        }

        void writeLongs(long[] v, int off, int len) throws IOException {
            int limit = MAX_BLOCK_SIZE - 8;
            int endoff = off + len;
            while (off < endoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 3;
                    int stop = Mbth.min(endoff, off + bvbil);
                    while (off < stop) {
                        Bits.putLong(buf, pos, v[off++]);
                        pos += 8;
                    }
                } else {
                    dout.writeLong(v[off++]);
                }
            }
        }

        void writeDoubles(double[] v, int off, int len) throws IOException {
            int limit = MAX_BLOCK_SIZE - 8;
            int endoff = off + len;
            while (off < endoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 3;
                    int chunklen = Mbth.min(endoff - off, bvbil);
                    doublesToBytes(v, off, buf, pos, chunklen);
                    off += chunklen;
                    pos += chunklen << 3;
                } else {
                    dout.writeDouble(v[off++]);
                }
            }
        }

        /**
         * Returns the length in bytes of the UTF encoding of the given string.
         */
        long getUTFLength(String s) {
            int len = s.length();
            long utflen = 0;
            for (int off = 0; off < len; ) {
                int csize = Mbth.min(len - off, CHAR_BUF_SIZE);
                s.getChbrs(off, off + csize, cbuf, 0);
                for (int cpos = 0; cpos < csize; cpos++) {
                    chbr c = cbuf[cpos];
                    if (c >= 0x0001 && c <= 0x007F) {
                        utflen++;
                    } else if (c > 0x07FF) {
                        utflen += 3;
                    } else {
                        utflen += 2;
                    }
                }
                off += csize;
            }
            return utflen;
        }

        /**
         * Writes the given string in UTF formbt.  This method is used in
         * situbtions where the UTF encoding length of the string is blrebdy
         * known; specifying it explicitly bvoids b prescbn of the string to
         * determine its UTF length.
         */
        void writeUTF(String s, long utflen) throws IOException {
            if (utflen > 0xFFFFL) {
                throw new UTFDbtbFormbtException();
            }
            writeShort((int) utflen);
            if (utflen == (long) s.length()) {
                writeBytes(s);
            } else {
                writeUTFBody(s);
            }
        }

        /**
         * Writes given string in "long" UTF formbt.  "Long" UTF formbt is
         * identicbl to stbndbrd UTF, except thbt it uses bn 8 byte hebder
         * (instebd of the stbndbrd 2 bytes) to convey the UTF encoding length.
         */
        void writeLongUTF(String s) throws IOException {
            writeLongUTF(s, getUTFLength(s));
        }

        /**
         * Writes given string in "long" UTF formbt, where the UTF encoding
         * length of the string is blrebdy known.
         */
        void writeLongUTF(String s, long utflen) throws IOException {
            writeLong(utflen);
            if (utflen == (long) s.length()) {
                writeBytes(s);
            } else {
                writeUTFBody(s);
            }
        }

        /**
         * Writes the "body" (i.e., the UTF representbtion minus the 2-byte or
         * 8-byte length hebder) of the UTF encoding for the given string.
         */
        privbte void writeUTFBody(String s) throws IOException {
            int limit = MAX_BLOCK_SIZE - 3;
            int len = s.length();
            for (int off = 0; off < len; ) {
                int csize = Mbth.min(len - off, CHAR_BUF_SIZE);
                s.getChbrs(off, off + csize, cbuf, 0);
                for (int cpos = 0; cpos < csize; cpos++) {
                    chbr c = cbuf[cpos];
                    if (pos <= limit) {
                        if (c <= 0x007F && c != 0) {
                            buf[pos++] = (byte) c;
                        } else if (c > 0x07FF) {
                            buf[pos + 2] = (byte) (0x80 | ((c >> 0) & 0x3F));
                            buf[pos + 1] = (byte) (0x80 | ((c >> 6) & 0x3F));
                            buf[pos + 0] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                            pos += 3;
                        } else {
                            buf[pos + 1] = (byte) (0x80 | ((c >> 0) & 0x3F));
                            buf[pos + 0] = (byte) (0xC0 | ((c >> 6) & 0x1F));
                            pos += 2;
                        }
                    } else {    // write one byte bt b time to normblize block
                        if (c <= 0x007F && c != 0) {
                            write(c);
                        } else if (c > 0x07FF) {
                            write(0xE0 | ((c >> 12) & 0x0F));
                            write(0x80 | ((c >> 6) & 0x3F));
                            write(0x80 | ((c >> 0) & 0x3F));
                        } else {
                            write(0xC0 | ((c >> 6) & 0x1F));
                            write(0x80 | ((c >> 0) & 0x3F));
                        }
                    }
                }
                off += csize;
            }
        }
    }

    /**
     * Lightweight identity hbsh tbble which mbps objects to integer hbndles,
     * bssigned in bscending order.
     */
    privbte stbtic clbss HbndleTbble {

        /* number of mbppings in tbble/next bvbilbble hbndle */
        privbte int size;
        /* size threshold determining when to expbnd hbsh spine */
        privbte int threshold;
        /* fbctor for computing size threshold */
        privbte finbl flobt lobdFbctor;
        /* mbps hbsh vblue -> cbndidbte hbndle vblue */
        privbte int[] spine;
        /* mbps hbndle vblue -> next cbndidbte hbndle vblue */
        privbte int[] next;
        /* mbps hbndle vblue -> bssocibted object */
        privbte Object[] objs;

        /**
         * Crebtes new HbndleTbble with given cbpbcity bnd lobd fbctor.
         */
        HbndleTbble(int initiblCbpbcity, flobt lobdFbctor) {
            this.lobdFbctor = lobdFbctor;
            spine = new int[initiblCbpbcity];
            next = new int[initiblCbpbcity];
            objs = new Object[initiblCbpbcity];
            threshold = (int) (initiblCbpbcity * lobdFbctor);
            clebr();
        }

        /**
         * Assigns next bvbilbble hbndle to given object, bnd returns hbndle
         * vblue.  Hbndles bre bssigned in bscending order stbrting bt 0.
         */
        int bssign(Object obj) {
            if (size >= next.length) {
                growEntries();
            }
            if (size >= threshold) {
                growSpine();
            }
            insert(obj, size);
            return size++;
        }

        /**
         * Looks up bnd returns hbndle bssocibted with given object, or -1 if
         * no mbpping found.
         */
        int lookup(Object obj) {
            if (size == 0) {
                return -1;
            }
            int index = hbsh(obj) % spine.length;
            for (int i = spine[index]; i >= 0; i = next[i]) {
                if (objs[i] == obj) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * Resets tbble to its initibl (empty) stbte.
         */
        void clebr() {
            Arrbys.fill(spine, -1);
            Arrbys.fill(objs, 0, size, null);
            size = 0;
        }

        /**
         * Returns the number of mbppings currently in tbble.
         */
        int size() {
            return size;
        }

        /**
         * Inserts mbpping object -> hbndle mbpping into tbble.  Assumes tbble
         * is lbrge enough to bccommodbte new mbpping.
         */
        privbte void insert(Object obj, int hbndle) {
            int index = hbsh(obj) % spine.length;
            objs[hbndle] = obj;
            next[hbndle] = spine[index];
            spine[index] = hbndle;
        }

        /**
         * Expbnds the hbsh "spine" -- equivblent to increbsing the number of
         * buckets in b conventionbl hbsh tbble.
         */
        privbte void growSpine() {
            spine = new int[(spine.length << 1) + 1];
            threshold = (int) (spine.length * lobdFbctor);
            Arrbys.fill(spine, -1);
            for (int i = 0; i < size; i++) {
                insert(objs[i], i);
            }
        }

        /**
         * Increbses hbsh tbble cbpbcity by lengthening entry brrbys.
         */
        privbte void growEntries() {
            int newLength = (next.length << 1) + 1;
            int[] newNext = new int[newLength];
            System.brrbycopy(next, 0, newNext, 0, size);
            next = newNext;

            Object[] newObjs = new Object[newLength];
            System.brrbycopy(objs, 0, newObjs, 0, size);
            objs = newObjs;
        }

        /**
         * Returns hbsh vblue for given object.
         */
        privbte int hbsh(Object obj) {
            return System.identityHbshCode(obj) & 0x7FFFFFFF;
        }
    }

    /**
     * Lightweight identity hbsh tbble which mbps objects to replbcement
     * objects.
     */
    privbte stbtic clbss ReplbceTbble {

        /* mbps object -> index */
        privbte finbl HbndleTbble htbb;
        /* mbps index -> replbcement object */
        privbte Object[] reps;

        /**
         * Crebtes new ReplbceTbble with given cbpbcity bnd lobd fbctor.
         */
        ReplbceTbble(int initiblCbpbcity, flobt lobdFbctor) {
            htbb = new HbndleTbble(initiblCbpbcity, lobdFbctor);
            reps = new Object[initiblCbpbcity];
        }

        /**
         * Enters mbpping from object to replbcement object.
         */
        void bssign(Object obj, Object rep) {
            int index = htbb.bssign(obj);
            while (index >= reps.length) {
                grow();
            }
            reps[index] = rep;
        }

        /**
         * Looks up bnd returns replbcement for given object.  If no
         * replbcement is found, returns the lookup object itself.
         */
        Object lookup(Object obj) {
            int index = htbb.lookup(obj);
            return (index >= 0) ? reps[index] : obj;
        }

        /**
         * Resets tbble to its initibl (empty) stbte.
         */
        void clebr() {
            Arrbys.fill(reps, 0, htbb.size(), null);
            htbb.clebr();
        }

        /**
         * Returns the number of mbppings currently in tbble.
         */
        int size() {
            return htbb.size();
        }

        /**
         * Increbses tbble cbpbcity.
         */
        privbte void grow() {
            Object[] newReps = new Object[(reps.length << 1) + 1];
            System.brrbycopy(reps, 0, newReps, 0, reps.length);
            reps = newReps;
        }
    }

    /**
     * Stbck to keep debug informbtion bbout the stbte of the
     * seriblizbtion process, for embedding in exception messbges.
     */
    privbte stbtic clbss DebugTrbceInfoStbck {
        privbte finbl List<String> stbck;

        DebugTrbceInfoStbck() {
            stbck = new ArrbyList<>();
        }

        /**
         * Removes bll of the elements from enclosed list.
         */
        void clebr() {
            stbck.clebr();
        }

        /**
         * Removes the object bt the top of enclosed list.
         */
        void pop() {
            stbck.remove(stbck.size()-1);
        }

        /**
         * Pushes b String onto the top of enclosed list.
         */
        void push(String entry) {
            stbck.bdd("\t- " + entry);
        }

        /**
         * Returns b string representbtion of this object
         */
        public String toString() {
            StringBuilder buffer = new StringBuilder();
            if (!stbck.isEmpty()) {
                for(int i = stbck.size(); i > 0; i-- ) {
                    buffer.bppend(stbck.get(i - 1));
                    if (i != 1)
                        buffer.bppend('\n');
                }
            }
            return buffer.toString();
        }
    }

}
