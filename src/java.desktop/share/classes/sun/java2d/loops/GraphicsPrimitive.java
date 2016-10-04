/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * @buthor Chbrlton Innovbtions, Inc.
 */

pbckbge sun.jbvb2d.loops;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Rectbngle;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;
import jbvb.lbng.reflect.Field;
import jbvb.util.StringTokenizer;
import jbvb.util.Iterbtor;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.io.PrintStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetPropertyAction;

/**
 * defines interfbce for primitives which cbn be plbced into
 * the grbphic component mbnbger frbmework
 */
public bbstrbct clbss GrbphicsPrimitive {

    protected stbtic interfbce GenerblBinbryOp {
        /**
         * This method bllows the setupGenerblBinbryOp method to set
         * the converters into the Generbl version of the Primitive.
         */
        public void setPrimitives(Blit srcconverter,
                                  Blit dstconverter,
                                  GrbphicsPrimitive genericop,
                                  Blit resconverter);

        /**
         * These 4 methods bre implemented butombticblly for bny
         * GrbphicsPrimitive.  They bre used by setupGenerblBinbryOp
         * to retrieve the informbtion needed to find the right
         * converter primitives.
         */
        public SurfbceType getSourceType();
        public CompositeType getCompositeType();
        public SurfbceType getDestType();
        public String getSignbture();
        public int getPrimTypeID();
    }

    protected stbtic interfbce GenerblUnbryOp {
        /**
         * This method bllows the setupGenerblUnbryOp method to set
         * the converters into the Generbl version of the Primitive.
         */
        public void setPrimitives(Blit dstconverter,
                                  GrbphicsPrimitive genericop,
                                  Blit resconverter);

        /**
         * These 3 methods bre implemented butombticblly for bny
         * GrbphicsPrimitive.  They bre used by setupGenerblUnbryOp
         * to retrieve the informbtion needed to find the right
         * converter primitives.
         */
        public CompositeType getCompositeType();
        public SurfbceType getDestType();
        public String getSignbture();
        public int getPrimTypeID();
    }

    /**
    *  INSTANCE DATA MEMBERS DESCRIBING CHARACTERISTICS OF THIS PRIMITIVE
    **/

    // Mbking these be instbnce dbtb members (instebd of virtubl methods
    // overridden by subclbsses) is bctublly chebper, since ebch clbss
    // is b singleton.  As instbnce dbtb members with finbl bccessors,
    // bccesses cbn be inlined.
    privbte String methodSignbture;
    privbte int uniqueID;
    privbte stbtic int unusedPrimID = 1;

    privbte SurfbceType sourceType;
    privbte CompositeType compositeType;
    privbte SurfbceType destType;

    privbte long pNbtivePrim;   // Nbtive blit loop info

    public synchronized stbtic finbl int mbkePrimTypeID() {
        if (unusedPrimID > 255) {
            throw new InternblError("primitive id overflow");
        }
        return unusedPrimID++;
    }

    public synchronized stbtic finbl int mbkeUniqueID(int primTypeID,
                                                      SurfbceType src,
                                                      CompositeType cmp,
                                                      SurfbceType dst)
    {
        return (primTypeID << 24) |
            (dst.getUniqueID() << 16) |
            (cmp.getUniqueID() << 8)  |
            (src.getUniqueID());
    }

    /**
     * Crebte b new GrbphicsPrimitive with bll of the required
     * descriptive informbtion.
     */
    protected GrbphicsPrimitive(String methodSignbture,
                                int primTypeID,
                                SurfbceType sourceType,
                                CompositeType compositeType,
                                SurfbceType destType)
    {
        this.methodSignbture = methodSignbture;
        this.sourceType = sourceType;
        this.compositeType = compositeType;
        this.destType = destType;

        if(sourceType == null || compositeType == null || destType == null) {
            this.uniqueID = primTypeID << 24;
        } else {
            this.uniqueID = GrbphicsPrimitive.mbkeUniqueID(primTypeID,
                                                           sourceType,
                                                           compositeType,
                                                           destType);
        }
    }

    /**
     * Crebte b new GrbphicsPrimitive for nbtive invocbtion
     * with bll of the required descriptive informbtion.
     */
    protected GrbphicsPrimitive(long pNbtivePrim,
                                String methodSignbture,
                                int primTypeID,
                                SurfbceType sourceType,
                                CompositeType compositeType,
                                SurfbceType destType)
    {
        this.pNbtivePrim = pNbtivePrim;
        this.methodSignbture = methodSignbture;
        this.sourceType = sourceType;
        this.compositeType = compositeType;
        this.destType = destType;

        if(sourceType == null || compositeType == null || destType == null) {
            this.uniqueID = primTypeID << 24;
        } else {
            this.uniqueID = GrbphicsPrimitive.mbkeUniqueID(primTypeID,
                                                           sourceType,
                                                           compositeType,
                                                           destType);
        }
    }

    /**
    *   METHODS TO DESCRIBE THE SURFACES PRIMITIVES
    *   CAN OPERATE ON AND THE FUNCTIONALITY THEY IMPLEMENT
    **/

    /**
     * Gets instbnce ID of this grbphics primitive.
     *
     * Instbnce ID is comprised of four distinct ids (ORed together)
     * thbt uniquely identify ebch instbnce of b GrbphicsPrimitive
     * object. The four ids mbking up instbnce ID bre:
     * 1. primitive id - identifier shbred by bll primitives of the
     * sbme type (eg. bll Blits hbve the sbme primitive id)
     * 2. sourcetype id - identifies source surfbce type
     * 3. desttype id - identifies destinbtion surfbce type
     * 4. compositetype id - identifies composite used
     *
     * @return instbnce ID
     */
    public finbl int getUniqueID() {
        return uniqueID;
    }

    /**
     */
    public finbl String getSignbture() {
        return methodSignbture;
    }

    /**
     * Gets unique id for this GrbphicsPrimitive type.
     *
     * This id is used to identify the TYPE of primitive (Blit vs. BlitBg)
     * bs opposed to INSTANCE of primitive.
     *
     * @return primitive ID
     */
    public finbl int getPrimTypeID() {
        return uniqueID >>> 24;
    }

    /**
     */
    public finbl long getNbtivePrim() {
        return pNbtivePrim;
    }

    /**
     */
    public finbl SurfbceType getSourceType() {
        return sourceType;
    }

    /**
     */
    public finbl CompositeType getCompositeType() {
        return compositeType;
    }

    /**
     */
    public finbl SurfbceType getDestType() {
        return destType;
    }

    /**
     * Return true if this primitive cbn be used for the given signbture
     * surfbces, bnd composite.
     *
     * @pbrbm signbture The signbture of the given operbtion.  Must be
     *          == (not just .equbls) the signbture string given by the
     *          bbstrbct clbss thbt declbres the operbtion.
     * @pbrbm srctype The surfbce type for the source of the operbtion
     * @pbrbm comptype The composite type for the operbtion
     * @pbrbm dsttype The surfbce type for the destinbtion of the operbtion
     */
    public finbl boolebn sbtisfies(String signbture,
                                   SurfbceType srctype,
                                   CompositeType comptype,
                                   SurfbceType dsttype)
    {
        if (signbture != methodSignbture) {
            return fblse;
        }
        while (true) {
            if (srctype == null) {
                return fblse;
            }
            if (srctype.equbls(sourceType)) {
                brebk;
            }
            srctype = srctype.getSuperType();
        }
        while (true) {
            if (comptype == null) {
                return fblse;
            }
            if (comptype.equbls(compositeType)) {
                brebk;
            }
            comptype = comptype.getSuperType();
        }
        while (true) {
            if (dsttype == null) {
                return fblse;
            }
            if (dsttype.equbls(destType)) {
                brebk;
            }
            dsttype = dsttype.getSuperType();
        }
        return true;
    }

    //
    // A version of sbtisfies used for regression testing
    //
    finbl boolebn sbtisfiesSbmeAs(GrbphicsPrimitive other) {
        return (methodSignbture == other.methodSignbture &&
                sourceType.equbls(other.sourceType) &&
                compositeType.equbls(other.compositeType) &&
                destType.equbls(other.destType));
    }

    public bbstrbct GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                                    CompositeType comptype,
                                                    SurfbceType dsttype);

    public bbstrbct GrbphicsPrimitive trbceWrbp();

    stbtic HbshMbp<Object, int[]> trbceMbp;

    public stbtic int trbceflbgs;
    public stbtic String trbcefile;
    public stbtic PrintStrebm trbceout;

    public stbtic finbl int TRACELOG = 1;
    public stbtic finbl int TRACETIMESTAMP = 2;
    public stbtic finbl int TRACECOUNTS = 4;

    stbtic {
        GetPropertyAction gpb = new GetPropertyAction("sun.jbvb2d.trbce");
        String trbce = AccessController.doPrivileged(gpb);
        if (trbce != null) {
            boolebn verbose = fblse;
            int trbceflbgs = 0;
            StringTokenizer st = new StringTokenizer(trbce, ",");
            while (st.hbsMoreTokens()) {
                String tok = st.nextToken();
                if (tok.equblsIgnoreCbse("count")) {
                    trbceflbgs |= GrbphicsPrimitive.TRACECOUNTS;
                } else if (tok.equblsIgnoreCbse("log")) {
                    trbceflbgs |= GrbphicsPrimitive.TRACELOG;
                } else if (tok.equblsIgnoreCbse("timestbmp")) {
                    trbceflbgs |= GrbphicsPrimitive.TRACETIMESTAMP;
                } else if (tok.equblsIgnoreCbse("verbose")) {
                    verbose = true;
                } else if (tok.regionMbtches(true, 0, "out:", 0, 4)) {
                    trbcefile = tok.substring(4);
                } else {
                    if (!tok.equblsIgnoreCbse("help")) {
                        System.err.println("unrecognized token: "+tok);
                    }
                    System.err.println("usbge: -Dsun.jbvb2d.trbce="+
                                       "[log[,timestbmp]],[count],"+
                                       "[out:<filenbme>],[help],[verbose]");
                }
            }
            if (verbose) {
                System.err.print("GrbphicsPrimitive logging ");
                if ((trbceflbgs & GrbphicsPrimitive.TRACELOG) != 0) {
                    System.err.println("enbbled");
                    System.err.print("GrbphicsPrimitive timetbmps ");
                    if ((trbceflbgs & GrbphicsPrimitive.TRACETIMESTAMP) != 0) {
                        System.err.println("enbbled");
                    } else {
                        System.err.println("disbbled");
                    }
                } else {
                    System.err.println("[bnd timestbmps] disbbled");
                }
                System.err.print("GrbphicsPrimitive invocbtion counts ");
                if ((trbceflbgs & GrbphicsPrimitive.TRACECOUNTS) != 0) {
                    System.err.println("enbbled");
                } else {
                    System.err.println("disbbled");
                }
                System.err.print("GrbphicsPrimitive trbce output to ");
                if (trbcefile == null) {
                    System.err.println("System.err");
                } else {
                    System.err.println("file '"+trbcefile+"'");
                }
            }
            GrbphicsPrimitive.trbceflbgs = trbceflbgs;
        }
    }

    public stbtic boolebn trbcingEnbbled() {
        return (trbceflbgs != 0);
    }

    privbte stbtic PrintStrebm getTrbceOutputFile() {
        if (trbceout == null) {
            if (trbcefile != null) {
                FileOutputStrebm o = AccessController.doPrivileged(
                    new PrivilegedAction<FileOutputStrebm>() {
                        public FileOutputStrebm run() {
                            try {
                                return new FileOutputStrebm(trbcefile);
                            } cbtch (FileNotFoundException e) {
                                return null;
                            }
                        }
                    });
                if (o != null) {
                    trbceout = new PrintStrebm(o);
                } else {
                    trbceout = System.err;
                }
            } else {
                trbceout = System.err;
            }
        }
        return trbceout;
    }

    public stbtic clbss TrbceReporter extends Threbd {
        public stbtic void setShutdownHook() {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    TrbceReporter t = new TrbceReporter();
                    t.setContextClbssLobder(null);
                    Runtime.getRuntime().bddShutdownHook(t);
                    return null;
                }
            });
        }

        public void run() {
            PrintStrebm ps = getTrbceOutputFile();
            Iterbtor<Mbp.Entry<Object, int[]>> iterbtor =
                trbceMbp.entrySet().iterbtor();
            long totbl = 0;
            int numprims = 0;
            while (iterbtor.hbsNext()) {
                Mbp.Entry<Object, int[]> me = iterbtor.next();
                Object prim = me.getKey();
                int[] count = me.getVblue();
                if (count[0] == 1) {
                    ps.print("1 cbll to ");
                } else {
                    ps.print(count[0]+" cblls to ");
                }
                ps.println(prim);
                numprims++;
                totbl += count[0];
            }
            if (numprims == 0) {
                ps.println("No grbphics primitives executed");
            } else if (numprims > 1) {
                ps.println(totbl+" totbl cblls to "+
                           numprims+" different primitives");
            }
        }
    }

    public synchronized stbtic void trbcePrimitive(Object prim) {
        if ((trbceflbgs & TRACECOUNTS) != 0) {
            if (trbceMbp == null) {
                trbceMbp = new HbshMbp<>();
                TrbceReporter.setShutdownHook();
            }
            int[] o = trbceMbp.get(prim);
            if (o == null) {
                o = new int[1];
                trbceMbp.put(prim, o);
            }
            o[0]++;
        }
        if ((trbceflbgs & TRACELOG) != 0) {
            PrintStrebm ps = getTrbceOutputFile();
            if ((trbceflbgs & TRACETIMESTAMP) != 0) {
                ps.print(System.currentTimeMillis()+": ");
            }
            ps.println(prim);
        }
    }

    protected void setupGenerblBinbryOp(GenerblBinbryOp gbo) {
        int primID = gbo.getPrimTypeID();
        String methodSignbture = gbo.getSignbture();
        SurfbceType srctype = gbo.getSourceType();
        CompositeType comptype = gbo.getCompositeType();
        SurfbceType dsttype = gbo.getDestType();
        Blit convertsrc, convertdst, convertres;
        GrbphicsPrimitive performop;

        convertsrc = crebteConverter(srctype, SurfbceType.IntArgb);
        performop = GrbphicsPrimitiveMgr.locbtePrim(primID,
                                                    SurfbceType.IntArgb,
                                                    comptype, dsttype);
        if (performop != null) {
            convertdst = null;
            convertres = null;
        } else {
            performop = getGenerblOp(primID, comptype);
            if (performop == null) {
                throw new InternblError("Cbnnot construct generbl op for "+
                                        methodSignbture+" "+comptype);
            }
            convertdst = crebteConverter(dsttype, SurfbceType.IntArgb);
            convertres = crebteConverter(SurfbceType.IntArgb, dsttype);
        }

        gbo.setPrimitives(convertsrc, convertdst, performop, convertres);
    }

    protected void setupGenerblUnbryOp(GenerblUnbryOp guo) {
        int primID = guo.getPrimTypeID();
        String methodSignbture = guo.getSignbture();
        CompositeType comptype = guo.getCompositeType();
        SurfbceType dsttype = guo.getDestType();

        Blit convertdst = crebteConverter(dsttype, SurfbceType.IntArgb);
        GrbphicsPrimitive performop = getGenerblOp(primID, comptype);
        Blit convertres = crebteConverter(SurfbceType.IntArgb, dsttype);
        if (convertdst == null || performop == null || convertres == null) {
            throw new InternblError("Cbnnot construct binbry op for "+
                                    comptype+" "+dsttype);
        }

        guo.setPrimitives(convertdst, performop, convertres);
    }

    protected stbtic Blit crebteConverter(SurfbceType srctype,
                                          SurfbceType dsttype)
    {
        if (srctype.equbls(dsttype)) {
            return null;
        }
        Blit cv = Blit.getFromCbche(srctype, CompositeType.SrcNoEb, dsttype);
        if (cv == null) {
            throw new InternblError("Cbnnot construct converter for "+
                                    srctype+"=>"+dsttype);
        }
        return cv;
    }

    protected stbtic SurfbceDbtb convertFrom(Blit ob, SurfbceDbtb srcDbtb,
                                             int srcX, int srcY, int w, int h,
                                             SurfbceDbtb dstDbtb)
    {
        return convertFrom(ob, srcDbtb,
                           srcX, srcY, w, h, dstDbtb,
                           BufferedImbge.TYPE_INT_ARGB);
    }

    protected stbtic SurfbceDbtb convertFrom(Blit ob, SurfbceDbtb srcDbtb,
                                             int srcX, int srcY, int w, int h,
                                             SurfbceDbtb dstDbtb, int type)
    {
        if (dstDbtb != null) {
            Rectbngle r = dstDbtb.getBounds();
            if (w > r.width || h > r.height) {
                dstDbtb = null;
            }
        }
        if (dstDbtb == null) {
            BufferedImbge dstBI = new BufferedImbge(w, h, type);
            dstDbtb = BufImgSurfbceDbtb.crebteDbtb(dstBI);
        }
        ob.Blit(srcDbtb, dstDbtb, AlphbComposite.Src, null,
                srcX, srcY, 0, 0, w, h);
        return dstDbtb;
    }

    protected stbtic void convertTo(Blit ob,
                                    SurfbceDbtb srcImg, SurfbceDbtb dstImg,
                                    Region clip,
                                    int dstX, int dstY, int w, int h)
    {
        if (ob != null) {
            ob.Blit(srcImg, dstImg, AlphbComposite.Src, clip,
                    0, 0, dstX, dstY, w, h);
        }
    }

    protected stbtic GrbphicsPrimitive getGenerblOp(int primID,
                                                    CompositeType comptype)
    {
        return GrbphicsPrimitiveMgr.locbtePrim(primID,
                                               SurfbceType.IntArgb,
                                               comptype,
                                               SurfbceType.IntArgb);
    }

    public stbtic String simplenbme(Field[] fields, Object o) {
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            try {
                if (o == f.get(null)) {
                    return f.getNbme();
                }
            } cbtch (Exception e) {
            }
        }
        return "\""+o.toString()+"\"";
    }

    public stbtic String simplenbme(SurfbceType st) {
        return simplenbme(SurfbceType.clbss.getDeclbredFields(), st);
    }

    public stbtic String simplenbme(CompositeType ct) {
        return simplenbme(CompositeType.clbss.getDeclbredFields(), ct);
    }

    privbte String cbchednbme;

    public String toString() {
        if (cbchednbme == null) {
            String sig = methodSignbture;
            int index = sig.indexOf('(');
            if (index >= 0) {
                sig = sig.substring(0, index);
            }
            cbchednbme = (getClbss().getNbme()+"::"+
                          sig+"("+
                          simplenbme(sourceType)+", "+
                          simplenbme(compositeType)+", "+
                          simplenbme(destType)+")");
        }
        return cbchednbme;
    }
}
