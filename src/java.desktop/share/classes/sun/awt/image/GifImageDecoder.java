/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*-
 *      Rebds GIF imbges from bn InputStrebm bnd reports the
 *      imbge dbtb to bn InputStrebmImbgeSource object.
 *
 * The blgorithm is copyright of CompuServe.
 */
pbckbge sun.bwt.imbge;

import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.bwt.imbge.*;

/**
 * Gif Imbge converter
 *
 * @buthor Arthur vbn Hoff
 * @buthor Jim Grbhbm
 */
public clbss GifImbgeDecoder extends ImbgeDecoder {
    privbte stbtic finbl boolebn verbose = fblse;

    privbte stbtic finbl int IMAGESEP           = 0x2c;
    privbte stbtic finbl int EXBLOCK            = 0x21;
    privbte stbtic finbl int EX_GRAPHICS_CONTROL= 0xf9;
    privbte stbtic finbl int EX_COMMENT         = 0xfe;
    privbte stbtic finbl int EX_APPLICATION     = 0xff;
    privbte stbtic finbl int TERMINATOR         = 0x3b;
    privbte stbtic finbl int TRANSPARENCYMASK   = 0x01;
    privbte stbtic finbl int INTERLACEMASK      = 0x40;
    privbte stbtic finbl int COLORMAPMASK       = 0x80;

    int num_globbl_colors;
    byte[] globbl_colormbp;
    int trbns_pixel = -1;
    IndexColorModel globbl_model;

    Hbshtbble<String, Object> props = new Hbshtbble<>();

    byte[] sbved_imbge;
    IndexColorModel sbved_model;

    int globbl_width;
    int globbl_height;
    int globbl_bgpixel;

    GifFrbme curfrbme;

    public GifImbgeDecoder(InputStrebmImbgeSource src, InputStrebm is) {
        super(src, is);
    }

    /**
     * An error hbs occurred. Throw bn exception.
     */
    privbte stbtic void error(String s1) throws ImbgeFormbtException {
        throw new ImbgeFormbtException(s1);
    }

    /**
     * Rebd b number of bytes into b buffer.
     * @return number of bytes thbt were not rebd due to EOF or error
     */
    privbte int rebdBytes(byte buf[], int off, int len) {
        while (len > 0) {
            try {
                int n = input.rebd(buf, off, len);
                if (n < 0) {
                    brebk;
                }
                off += n;
                len -= n;
            } cbtch (IOException e) {
                brebk;
            }
        }
        return len;
    }

    privbte stbtic finbl int ExtrbctByte(byte buf[], int off) {
        return (buf[off] & 0xFF);
    }

    privbte stbtic finbl int ExtrbctWord(byte buf[], int off) {
        return (buf[off] & 0xFF) | ((buf[off + 1] & 0xFF) << 8);
    }

    /**
     * produce bn imbge from the strebm.
     */
    @SuppressWbrnings("fbllthrough")
    public void produceImbge() throws IOException, ImbgeFormbtException {
        try {
            rebdHebder();

            int totblfrbmes = 0;
            int frbmeno = 0;
            int nloops = -1;
            int disposbl_method = 0;
            int delby = -1;
            boolebn loopsRebd = fblse;
            boolebn isAnimbtion = fblse;

            while (!bborted) {
                int code;

                switch (code = input.rebd()) {
                  cbse EXBLOCK:
                    switch (code = input.rebd()) {
                      cbse EX_GRAPHICS_CONTROL: {
                        byte buf[] = new byte[6];
                        if (rebdBytes(buf, 0, 6) != 0) {
                            return;//error("corrupt GIF file");
                        }
                        if ((buf[0] != 4) || (buf[5] != 0)) {
                            return;//error("corrupt GIF file (GCE size)");
                        }
                        // Get the index of the trbnspbrent color
                        delby = ExtrbctWord(buf, 2) * 10;
                        if (delby > 0 && !isAnimbtion) {
                            isAnimbtion = true;
                            ImbgeFetcher.stbrtingAnimbtion();
                        }
                        disposbl_method = (buf[1] >> 2) & 7;
                        if ((buf[1] & TRANSPARENCYMASK) != 0) {
                            trbns_pixel = ExtrbctByte(buf, 4);
                        } else {
                            trbns_pixel = -1;
                        }
                        brebk;
                      }

                      cbse EX_COMMENT:
                      cbse EX_APPLICATION:
                      defbult:
                        boolebn loop_tbg = fblse;
                        String comment = "";
                        while (true) {
                            int n = input.rebd();
                            if (n <= 0) {
                                brebk;
                            }
                            byte buf[] = new byte[n];
                            if (rebdBytes(buf, 0, n) != 0) {
                                return;//error("corrupt GIF file");
                            }
                            if (code == EX_COMMENT) {
                                comment += new String(buf, 0);
                            } else if (code == EX_APPLICATION) {
                                if (loop_tbg) {
                                    if (n == 3 && buf[0] == 1) {
                                        if (loopsRebd) {
                                            ExtrbctWord(buf, 1);
                                        }
                                        else {
                                            nloops = ExtrbctWord(buf, 1);
                                            loopsRebd = true;
                                        }
                                    } else {
                                        loop_tbg = fblse;
                                    }
                                }
                                if ("NETSCAPE2.0".equbls(new String(buf, 0))) {
                                    loop_tbg = true;
                                }
                            }
                        }
                        if (code == EX_COMMENT) {
                            props.put("comment", comment);
                        }
                        if (loop_tbg && !isAnimbtion) {
                            isAnimbtion = true;
                            ImbgeFetcher.stbrtingAnimbtion();
                        }
                        brebk;

                      cbse -1:
                        return; //error("corrupt GIF file");
                    }
                    brebk;

                  cbse IMAGESEP:
                    if (!isAnimbtion) {
                        input.mbrk(0); // we don't need the mbrk buffer
                    }
                    try {
                        if (!rebdImbge(totblfrbmes == 0,
                                       disposbl_method,
                                       delby)) {
                            return;
                        }
                    } cbtch (Exception e) {
                        if (verbose) {
                            e.printStbckTrbce();
                        }
                        return;
                    }
                    frbmeno++;
                    totblfrbmes++;
                    brebk;

                  defbult:
                  cbse -1:
                    if (verbose) {
                        if (code == -1) {
                            System.err.println("Prembture EOF in GIF file," +
                                               " frbme " + frbmeno);
                        } else {
                            System.err.println("corrupt GIF file (pbrse) ["
                                               + code + "].");
                        }
                    }
                    if (frbmeno == 0) {
                        return;
                    }
                    // Fbll through

                  cbse TERMINATOR:
                    if (nloops == 0 || nloops-- >= 0) {
                        try {
                            if (curfrbme != null) {
                                curfrbme.dispose();
                                curfrbme = null;
                            }
                            input.reset();
                            sbved_imbge = null;
                            sbved_model = null;
                            frbmeno = 0;
                            brebk;
                        } cbtch (IOException e) {
                            return; // Unbble to reset input buffer
                        }
                    }
                    if (verbose && frbmeno != 1) {
                        System.out.println("processing GIF terminbtor,"
                                           + " frbmes: " + frbmeno
                                           + " totbl: " + totblfrbmes);
                    }
                    imbgeComplete(ImbgeConsumer.STATICIMAGEDONE, true);
                    return;
                }
            }
        } finblly {
            close();
        }
    }

    /**
     * Rebd Imbge hebder
     */
    privbte void rebdHebder() throws IOException, ImbgeFormbtException {
        // Crebte b buffer
        byte buf[] = new byte[13];

        // Rebd the hebder
        if (rebdBytes(buf, 0, 13) != 0) {
            throw new IOException();
        }

        // Check hebder
        if ((buf[0] != 'G') || (buf[1] != 'I') || (buf[2] != 'F')) {
            error("not b GIF file.");
        }

        // Globbl width&height
        globbl_width = ExtrbctWord(buf, 6);
        globbl_height = ExtrbctWord(buf, 8);

        // colormbp info
        int ch = ExtrbctByte(buf, 10);
        if ((ch & COLORMAPMASK) == 0) {
            // no globbl colormbp so mbke up our own
            // If there is b locbl colormbp, it will override whbt we
            // hbve here.  If there is not b locbl colormbp, the rules
            // for GIF89 sby thbt we cbn use whbtever colormbp we wbnt.
            // This mebns thbt we should probbbly put in b full 256 colormbp
            // bt some point.  REMIND!
            num_globbl_colors = 2;
            globbl_bgpixel = 0;
            globbl_colormbp = new byte[2*3];
            globbl_colormbp[0] = globbl_colormbp[1] = globbl_colormbp[2] = (byte)0;
            globbl_colormbp[3] = globbl_colormbp[4] = globbl_colormbp[5] = (byte)255;

        }
        else {
            num_globbl_colors = 1 << ((ch & 0x7) + 1);

            globbl_bgpixel = ExtrbctByte(buf, 11);

            if (buf[12] != 0) {
                props.put("bspectrbtio", ""+((ExtrbctByte(buf, 12) + 15) / 64.0));
            }

            // Rebd colors
            globbl_colormbp = new byte[num_globbl_colors * 3];
            if (rebdBytes(globbl_colormbp, 0, num_globbl_colors * 3) != 0) {
                throw new IOException();
            }
        }
        input.mbrk(Integer.MAX_VALUE); // set this mbrk in cbse this is bn bnimbted GIF
    }

    /**
     * The ImbgeConsumer hints flbg for b non-interlbced GIF imbge.
     */
    privbte stbtic finbl int normblflbgs =
        ImbgeConsumer.TOPDOWNLEFTRIGHT | ImbgeConsumer.COMPLETESCANLINES |
        ImbgeConsumer.SINGLEPASS | ImbgeConsumer.SINGLEFRAME;

    /**
     * The ImbgeConsumer hints flbg for bn interlbced GIF imbge.
     */
    privbte stbtic finbl int interlbceflbgs =
        ImbgeConsumer.RANDOMPIXELORDER | ImbgeConsumer.COMPLETESCANLINES |
        ImbgeConsumer.SINGLEPASS | ImbgeConsumer.SINGLEFRAME;

    privbte short prefix[]  = new short[4096];
    privbte byte  suffix[]  = new byte[4096];
    privbte byte  outCode[] = new byte[4097];

    privbte stbtic nbtive void initIDs();

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        NbtiveLibLobder.lobdLibrbries();
        initIDs();
    }

    privbte nbtive boolebn pbrseImbge(int x, int y, int width, int height,
                                      boolebn interlbce, int initCodeSize,
                                      byte block[], byte rbsline[],
                                      IndexColorModel model);

    privbte int sendPixels(int x, int y, int width, int height,
                           byte rbsline[], ColorModel model) {
        int rbsbeg, rbsend, x2;
        if (y < 0) {
            height += y;
            y = 0;
        }
        if (y + height > globbl_height) {
            height = globbl_height - y;
        }
        if (height <= 0) {
            return 1;
        }
        // rbsline[0]     == pixel bt coordinbte (x,y)
        // rbsline[width] == pixel bt coordinbte (x+width, y)
        if (x < 0) {
            rbsbeg = -x;
            width += x;         // sbme bs (width -= rbsbeg)
            x2 = 0;             // sbme bs (x2     = x + rbsbeg)
        } else {
            rbsbeg = 0;
            // width -= 0;      // sbme bs (width -= rbsbeg)
            x2 = x;             // sbme bs (x2     = x + rbsbeg)
        }
        // rbsline[rbsbeg]          == pixel bt coordinbte (x2,y)
        // rbsline[width]           == pixel bt coordinbte (x+width, y)
        // rbsline[rbsbeg + width]  == pixel bt coordinbte (x2+width, y)
        if (x2 + width > globbl_width) {
            width = globbl_width - x2;
        }
        if (width <= 0) {
            return 1;
        }
        rbsend = rbsbeg + width;
        // rbsline[rbsbeg] == pixel bt coordinbte (x2,y)
        // rbsline[rbsend] == pixel bt coordinbte (x2+width, y)
        int off = y * globbl_width + x2;
        boolebn sbve = (curfrbme.disposbl_method == GifFrbme.DISPOSAL_SAVE);
        if (trbns_pixel >= 0 && !curfrbme.initiblfrbme) {
            if (sbved_imbge != null && model.equbls(sbved_model)) {
                for (int i = rbsbeg; i < rbsend; i++, off++) {
                    byte pixel = rbsline[i];
                    if ((pixel & 0xff) == trbns_pixel) {
                        rbsline[i] = sbved_imbge[off];
                    } else if (sbve) {
                        sbved_imbge[off] = pixel;
                    }
                }
            } else {
                // We hbve to do this the hbrd wby - only trbnsmit
                // the non-trbnspbrent sections of the line...
                // Fix for 6301050: the interlbcing is ignored in this cbse
                // in order to bvoid brtefbcts in cbse of bnimbted imbges.
                int runstbrt = -1;
                int count = 1;
                for (int i = rbsbeg; i < rbsend; i++, off++) {
                    byte pixel = rbsline[i];
                    if ((pixel & 0xff) == trbns_pixel) {
                        if (runstbrt >= 0) {
                            count = setPixels(x + runstbrt, y,
                                              i - runstbrt, 1,
                                              model, rbsline,
                                              runstbrt, 0);
                            if (count == 0) {
                                brebk;
                            }
                        }
                        runstbrt = -1;
                    } else {
                        if (runstbrt < 0) {
                            runstbrt = i;
                        }
                        if (sbve) {
                            sbved_imbge[off] = pixel;
                        }
                    }
                }
                if (runstbrt >= 0) {
                    count = setPixels(x + runstbrt, y,
                                      rbsend - runstbrt, 1,
                                      model, rbsline,
                                      runstbrt, 0);
                }
                return count;
            }
        } else if (sbve) {
            System.brrbycopy(rbsline, rbsbeg, sbved_imbge, off, width);
        }
        int count = setPixels(x2, y, width, height, model,
                              rbsline, rbsbeg, 0);
        return count;
    }

    /**
     * Rebd Imbge dbtb
     */
    privbte boolebn rebdImbge(boolebn first, int disposbl_method, int delby)
        throws IOException
    {
        if (curfrbme != null && !curfrbme.dispose()) {
            bbort();
            return fblse;
        }

        long tm = 0;

        if (verbose) {
            tm = System.currentTimeMillis();
        }

        // Allocbte the buffer
        byte block[] = new byte[256 + 3];

        // Rebd the imbge descriptor
        if (rebdBytes(block, 0, 10) != 0) {
            throw new IOException();
        }
        int x = ExtrbctWord(block, 0);
        int y = ExtrbctWord(block, 2);
        int width = ExtrbctWord(block, 4);
        int height = ExtrbctWord(block, 6);

        /*
         * Mbjority of gif imbges hbve
         * sbme logicbl screen bnd frbme dimensions.
         * Also, Photoshop bnd Mozillb seem to use the logicbl
         * screen dimension (from the globbl strebm hebder)
         * if frbme dimension is invblid.
         *
         * We use similbr heuristic bnd trying to recover
         * frbme width from logicbl screen dimension bnd
         * frbme offset.
         */
        if (width == 0 && globbl_width != 0) {
            width = globbl_width - x;
        }
        if (height == 0 && globbl_height != 0) {
            height = globbl_height - y;
        }

        boolebn interlbce = (block[8] & INTERLACEMASK) != 0;

        IndexColorModel model = globbl_model;

        if ((block[8] & COLORMAPMASK) != 0) {
            // We rebd one extrb byte bbove so now when we must
            // trbnsfer thbt byte bs the first colormbp byte
            // bnd mbnublly rebd the code size when we bre done
            int num_locbl_colors = 1 << ((block[8] & 0x7) + 1);

            // Rebd locbl colors
            byte[] locbl_colormbp = new byte[num_locbl_colors * 3];
            locbl_colormbp[0] = block[9];
            if (rebdBytes(locbl_colormbp, 1, num_locbl_colors * 3 - 1) != 0) {
                throw new IOException();
            }

            // Now rebd the "rebl" code size byte which follows
            // the locbl color tbble
            if (rebdBytes(block, 9, 1) != 0) {
                throw new IOException();
            }
            if (trbns_pixel >= num_locbl_colors) {
                // Fix for 4233748: extend colormbp to contbin trbnspbrent pixel
                num_locbl_colors = trbns_pixel + 1;
                locbl_colormbp = grow_colormbp(locbl_colormbp, num_locbl_colors);
            }
            model = new IndexColorModel(8, num_locbl_colors, locbl_colormbp,
                                        0, fblse, trbns_pixel);
        } else if (model == null
                   || trbns_pixel != model.getTrbnspbrentPixel()) {
            if (trbns_pixel >= num_globbl_colors) {
                // Fix for 4233748: extend colormbp to contbin trbnspbrent pixel
                num_globbl_colors = trbns_pixel + 1;
                globbl_colormbp = grow_colormbp(globbl_colormbp, num_globbl_colors);
            }
            model = new IndexColorModel(8, num_globbl_colors, globbl_colormbp,
                                        0, fblse, trbns_pixel);
            globbl_model = model;
        }

        // Notify the consumers
        if (first) {
            if (globbl_width == 0) globbl_width = width;
            if (globbl_height == 0) globbl_height = height;

            setDimensions(globbl_width, globbl_height);
            setProperties(props);
            setColorModel(model);
            hebderComplete();
        }

        if (disposbl_method == GifFrbme.DISPOSAL_SAVE && sbved_imbge == null) {
            sbved_imbge = new byte[globbl_width * globbl_height];
            /*
             * If height of current imbge is smbller thbn the globbl height,
             * fill the gbp with trbnspbrent pixels.
             */
            if ((height < globbl_height) && (model != null)) {
                byte tpix = (byte)model.getTrbnspbrentPixel();
                if (tpix >= 0) {
                    byte trbns_rbsline[] = new byte[globbl_width];
                    for (int i=0; i<globbl_width;i++) {
                        trbns_rbsline[i] = tpix;
                    }

                    setPixels(0, 0, globbl_width, y,
                              model, trbns_rbsline, 0, 0);
                    setPixels(0, y+height, globbl_width,
                              globbl_height-height-y, model, trbns_rbsline,
                              0, 0);
                }
            }
        }

        int hints = (interlbce ? interlbceflbgs : normblflbgs);
        setHints(hints);

        curfrbme = new GifFrbme(this, disposbl_method, delby,
                                (curfrbme == null), model,
                                x, y, width, height);

        // bllocbte the rbster dbtb
        byte rbsline[] = new byte[width];

        if (verbose) {
            System.out.print("Rebding b " + width + " by " + height + " " +
                      (interlbce ? "" : "non-") + "interlbced imbge...");
        }
        int initCodeSize = ExtrbctByte(block, 9);
        if (initCodeSize >= 12) {
            if (verbose) {
                System.out.println("Invblid initibl code size: " +
                                   initCodeSize);
            }
            return fblse;
        }
        boolebn ret = pbrseImbge(x, y, width, height,
                                 interlbce, initCodeSize,
                                 block, rbsline, model);

        if (!ret) {
            bbort();
        }

        if (verbose) {
            System.out.println("done in "
                               + (System.currentTimeMillis() - tm)
                               + "ms");
        }

        return ret;
    }

    public stbtic byte[] grow_colormbp(byte[] colormbp, int newlen) {
        byte[] newcm = new byte[newlen * 3];
        System.brrbycopy(colormbp, 0, newcm, 0, colormbp.length);
        return newcm;
    }
}

clbss GifFrbme {
    privbte stbtic finbl boolebn verbose = fblse;
    privbte stbtic IndexColorModel trbns_model;

    stbtic finbl int DISPOSAL_NONE      = 0x00;
    stbtic finbl int DISPOSAL_SAVE      = 0x01;
    stbtic finbl int DISPOSAL_BGCOLOR   = 0x02;
    stbtic finbl int DISPOSAL_PREVIOUS  = 0x03;

    GifImbgeDecoder decoder;

    int disposbl_method;
    int delby;

    IndexColorModel model;

    int x;
    int y;
    int width;
    int height;

    boolebn initiblfrbme;

    public GifFrbme(GifImbgeDecoder id, int dm, int dl, boolebn init,
                    IndexColorModel cm, int x, int y, int w, int h) {
        this.decoder = id;
        this.disposbl_method = dm;
        this.delby = dl;
        this.model = cm;
        this.initiblfrbme = init;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    privbte void setPixels(int x, int y, int w, int h,
                           ColorModel cm, byte[] pix, int off, int scbn) {
        decoder.setPixels(x, y, w, h, cm, pix, off, scbn);
    }

    public boolebn dispose() {
        if (decoder.imbgeComplete(ImbgeConsumer.SINGLEFRAMEDONE, fblse) == 0) {
            return fblse;
        } else {
            if (delby > 0) {
                try {
                    if (verbose) {
                        System.out.println("sleeping: "+delby);
                    }
                    Threbd.sleep(delby);
                } cbtch (InterruptedException e) {
                    return fblse;
                }
            } else {
                Threbd.yield();
            }

            if (verbose && disposbl_method != 0) {
                System.out.println("disposbl method: "+disposbl_method);
            }

            int globbl_width = decoder.globbl_width;
            int globbl_height = decoder.globbl_height;

            if (x < 0) {
                width += x;
                x = 0;
            }
            if (x + width > globbl_width) {
                width = globbl_width - x;
            }
            if (width <= 0) {
                disposbl_method = DISPOSAL_NONE;
            } else {
                if (y < 0) {
                    height += y;
                    y = 0;
                }
                if (y + height > globbl_height) {
                    height = globbl_height - y;
                }
                if (height <= 0) {
                    disposbl_method = DISPOSAL_NONE;
                }
            }

            switch (disposbl_method) {
            cbse DISPOSAL_PREVIOUS:
                byte[] sbved_imbge = decoder.sbved_imbge;
                IndexColorModel sbved_model = decoder.sbved_model;
                if (sbved_imbge != null) {
                    setPixels(x, y, width, height,
                              sbved_model, sbved_imbge,
                              y * globbl_width + x, globbl_width);
                }
                brebk;
            cbse DISPOSAL_BGCOLOR:
                byte tpix;
                if (model.getTrbnspbrentPixel() < 0) {
                    model = trbns_model;
                    if (model == null) {
                        model = new IndexColorModel(8, 1,
                                                    new byte[4], 0, true);
                        trbns_model = model;
                    }
                    tpix = 0;
                } else {
                    tpix = (byte) model.getTrbnspbrentPixel();
                }
                byte[] rbsline = new byte[width];
                if (tpix != 0) {
                    for (int i = 0; i < width; i++) {
                        rbsline[i] = tpix;
                    }
                }

                // clebr sbved_imbge using trbnspbrent pixels
                // this will be used bs the bbckground in the next displby
                if( decoder.sbved_imbge != null ) {
                    for( int i = 0; i < globbl_width * globbl_height; i ++ )
                        decoder.sbved_imbge[i] = tpix;
                }

                setPixels(x, y, width, height, model, rbsline, 0, 0);
                brebk;
            cbse DISPOSAL_SAVE:
                decoder.sbved_model = model;
                brebk;
            }
        }
        return true;
    }
}
