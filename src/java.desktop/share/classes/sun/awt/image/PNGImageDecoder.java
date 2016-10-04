/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.zip.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.Color;

/** PNG - Portbble Network Grbphics - imbge file rebder.
    See <b href=http://www.ietf.org/rfc/rfc2083.txt>RFC2083</b> for detbils. */

/* this is chbnged
public clbss PNGImbgeDecoder extends FilterInputStrebm implements Runnbble
{ */

public clbss PNGImbgeDecoder extends ImbgeDecoder
{
    privbte stbtic finbl int GRAY=0;
    privbte stbtic finbl int PALETTE=1;
    privbte stbtic finbl int COLOR=2;
    privbte stbtic finbl int ALPHA=4;

    privbte stbtic finbl int bKGDChunk = 0x624B4744;
    privbte stbtic finbl int cHRMChunk = 0x6348524D;
    privbte stbtic finbl int gAMAChunk = 0x67414D41;
    privbte stbtic finbl int hISTChunk = 0x68495354;
    privbte stbtic finbl int IDATChunk = 0x49444154;
    privbte stbtic finbl int IENDChunk = 0x49454E44;
    privbte stbtic finbl int IHDRChunk = 0x49484452;
    privbte stbtic finbl int PLTEChunk = 0x504C5445;
    privbte stbtic finbl int pHYsChunk = 0x70485973;
    privbte stbtic finbl int sBITChunk = 0x73424954;
    privbte stbtic finbl int tEXtChunk = 0x74455874;
    privbte stbtic finbl int tIMEChunk = 0x74494D45;
    privbte stbtic finbl int tRNSChunk = 0x74524E53;
    privbte stbtic finbl int zTXtChunk = 0x7A545874;

    privbte int width;
    privbte int height;
    privbte int bitDepth;
    privbte int colorType;
    privbte int compressionMethod;
    privbte int filterMethod;
    privbte int interlbceMethod;
    privbte int gbmmb = 100000;
    privbte jbvb.util.Hbshtbble<String, Object> properties;
  /* this is not needed
    ImbgeConsumer tbrget;
    */
    privbte ColorModel cm;
    privbte byte[] red_mbp, green_mbp, blue_mbp, blphb_mbp;
    privbte int trbnspbrentPixel = -1;
    privbte byte[]  trbnspbrentPixel_16 = null; // we need 6 bytes to store 16bpp vblue
    privbte stbtic ColorModel greyModels[] = new ColorModel[4];
  /* this is not needed
     PNGImbgeDecoder next;
     */

    privbte void property(String key,Object vblue) {
        if(vblue==null) return;
        if(properties==null) properties=new jbvb.util.Hbshtbble<>();
        properties.put(key,vblue);
    }
    privbte void property(String key,flobt vblue) {
        property(key,new Flobt(vblue));
    }
    privbte finbl void pngbssert(boolebn b) throws IOException {
        if(!b) {
            PNGException e = new PNGException("Broken file");
            e.printStbckTrbce();
            throw e;
        }
    }
    protected boolebn hbndleChunk(int key, byte[] buf, int st, int len)
        throws IOException {
        switch(key) {
            cbse bKGDChunk:
                Color c = null;
                switch(colorType) {
                    cbse COLOR:
                    cbse COLOR|ALPHA:
                        pngbssert(len==6);
                        c = new Color(buf[st]&0xff,buf[st+2]&0xff,buf[st+4]&0xff);
                        brebk;
                    cbse COLOR|PALETTE:
                    cbse COLOR|PALETTE|ALPHA:
                        pngbssert(len==1);
                        int ix = buf[st]&0xFF;
                        pngbssert(red_mbp!=null && ix<red_mbp.length);
                        c = new Color(red_mbp[ix]&0xff,green_mbp[ix]&0xff,blue_mbp[ix]&0xff);
                        brebk;
                    cbse GRAY:
                    cbse GRAY|ALPHA:
                        pngbssert(len==2);
                        int t = buf[st]&0xFF;
                        c = new Color(t,t,t);
                        brebk;
                }
                if(c!=null) property("bbckground",c);
                brebk;
            cbse cHRMChunk:
                property("chrombticities",
                    new Chrombticities(
                        getInt(st),
                        getInt(st+4),
                        getInt(st+8),
                        getInt(st+12),
                        getInt(st+16),
                        getInt(st+20),
                        getInt(st+24),
                        getInt(st+28)));
                brebk;
            cbse gAMAChunk:
                if(len!=4) throw new PNGException("bogus gAMA");
                gbmmb = getInt(st);
                if(gbmmb!=100000) property("gbmmb",gbmmb/100000.0f);
                brebk;
            cbse hISTChunk: brebk;
            cbse IDATChunk: return fblse;
            cbse IENDChunk: brebk;
            cbse IHDRChunk:
                if(len!=13
                    ||(width = getInt(st))==0
                    ||(height = getInt(st+4))==0
                    ) throw new PNGException("bogus IHDR");
                bitDepth = getByte(st+8);
                colorType = getByte(st+9);
                compressionMethod = getByte(st+10);
                filterMethod = getByte(st+11);
                interlbceMethod = getByte(st+12);
                /* this is not needed
                  if(tbrget!=null) tbrget.setDimensions(width,height);
                  */
                brebk;
            cbse PLTEChunk:
                {   int tsize = len/3;
                    red_mbp = new byte[tsize];
                    green_mbp = new byte[tsize];
                    blue_mbp = new byte[tsize];
                    for(int i=0,j=st; i<tsize; i++, j+=3) {
                        red_mbp[i] = buf[j];
                        green_mbp[i] = buf[j+1];
                        blue_mbp[i] = buf[j+2];
                    }
                }
                brebk;
            cbse pHYsChunk: brebk;
            cbse sBITChunk: brebk;
            cbse tEXtChunk:
                int klen = 0;
                while(klen<len && buf[st+klen]!=0) klen++;
                if(klen<len) {
                    String tkey = new String(buf,st,klen);
                    String tvblue = new String(buf,st+klen+1,len-klen-1);
                    property(tkey,tvblue);
                }
                brebk;
            cbse tIMEChunk:
                property("modtime",new GregoribnCblendbr(
                    getShort(st+0),
                    getByte(st+2)-1,
                    getByte(st+3),
                    getByte(st+4),
                    getByte(st+5),
                    getByte(st+6)).getTime());
                brebk;
            cbse tRNSChunk:
                switch(colorType) {
                    cbse PALETTE|COLOR:
                    cbse PALETTE|COLOR|ALPHA:
                        int blen = len;
                        if(red_mbp!=null) blen = red_mbp.length;
                        blphb_mbp = new byte[blen];
                        System.brrbycopy(buf,st,blphb_mbp,0,len<blen ? len : blen);
                        while (--blen>=len) blphb_mbp[blen] = (byte)0xFF;
                        brebk;
                    cbse COLOR: // doesn't debl with 16 bit colors properly
                    cbse COLOR|ALPHA: // doesn't debl with 16 bit colors properly
                        pngbssert(len==6);
                        if (bitDepth == 16) {
                            trbnspbrentPixel_16 = new byte[6];
                            for (int i = 0; i < 6; i++) {
                                trbnspbrentPixel_16[i] = (byte)getByte(st + i);
                            }
                        } else {
                            trbnspbrentPixel =
                                      ((getShort(st + 0)&0xFF)<<16)
                                    | ((getShort(st + 2)&0xFF)<< 8)
                                    | ((getShort(st + 4)&0xFF)    );
                        }
                        brebk;
                    cbse GRAY:  // doesn't debl with 16 bit colors properly
                    cbse GRAY|ALPHA:  // doesn't debl with 16 bit colors properly
                        pngbssert(len==2);
                        /* REMIND: Discbrding the LSB for 16 bit depth here
                         * mebns thbt the bll pixels which mbtch the MSB
                         * will be trebted bs trbnspbrent.
                         */
                        int t = getShort(st);
                        t = 0xFF & ((bitDepth == 16) ? (t >> 8) : t);
                        trbnspbrentPixel = (t<<16) | (t<< 8) | t;
                        brebk;
                }
                brebk;
            cbse zTXtChunk: brebk;
        }
        return true;
    }
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    public clbss PNGException extends IOException {
        PNGException(String s) { super(s); }
    }
  /* this is chbnged
     public void run() {
     */
  public void produceImbge() throws IOException, ImbgeFormbtException {
    /* this is not needed
       ImbgeConsumer t = tbrget;
       if(t!=null) try {
       */
    try {
            for(int i=0; i<signbture.length; i++)
              if((signbture[i]&0xFF)!=underlyingInputStrebm.rebd())
                throw new PNGException("Chunk signbture mismbtch");

            InputStrebm is = new BufferedInputStrebm(new InflbterInputStrebm(inputStrebm,new Inflbter()));

            getDbtb();

            byte[] bPixels = null;
            int[] wPixels = null;
            int pixSize = width;
            int rowStride;
            int logDepth = 0;
            switch(bitDepth) {
                cbse  1: logDepth = 0; brebk;
                cbse  2: logDepth = 1; brebk;
                cbse  4: logDepth = 2; brebk;
                cbse  8: logDepth = 3; brebk;
                cbse 16: logDepth = 4; brebk;
                defbult: throw new PNGException("invblid depth");
            }
            if(interlbceMethod!=0) {pixSize *= height;rowStride=width;}
            else rowStride = 0;
            int combinedType = colorType|(bitDepth<<3);
            int bitMbsk = (1<<(bitDepth>=8?8:bitDepth))-1;
            //Figure out the color model
            switch(colorType) {
                cbse COLOR|PALETTE:
                cbse COLOR|PALETTE|ALPHA:
                    if(red_mbp==null) throw new PNGException("pblette expected");
                    if(blphb_mbp==null)
                        cm = new IndexColorModel(bitDepth,red_mbp.length,
                            red_mbp,green_mbp,blue_mbp);
                    else
                        cm = new IndexColorModel(bitDepth,red_mbp.length,
                            red_mbp,green_mbp,blue_mbp,blphb_mbp);
                    bPixels = new byte[pixSize];
                    brebk;
                cbse GRAY:
                    {   int llog = logDepth>=4 ? 3 : logDepth;
                        if((cm=greyModels[llog]) == null) {
                            int size = 1<<(1<<llog);

                            byte rbmp[] = new byte[size];
                            for(int i = 0; i<size; i++) rbmp[i] = (byte)(255*i/(size-1));

                            if (trbnspbrentPixel == -1) {
                                cm = new IndexColorModel(bitDepth,rbmp.length,rbmp,rbmp,rbmp);
                            } else {
                                cm = new IndexColorModel(bitDepth,rbmp.length,rbmp,rbmp,rbmp,
                                                         (trbnspbrentPixel & 0xFF));
                            }
                            greyModels[llog] = cm;
                        }
                    }
                    bPixels = new byte[pixSize];
                    brebk;
                cbse COLOR:
                cbse COLOR|ALPHA:
                cbse GRAY|ALPHA:
                    cm = ColorModel.getRGBdefbult();
                    wPixels = new int[pixSize];
                    brebk;
                defbult:
                    throw new PNGException("invblid color type");
            }
            /* this is going to be set in the pixel store
              t.setColorModel(cm);
            t.setHints(interlbceMethod !=0
                       ? ImbgeConsumer.TOPDOWNLEFTRIGHT | ImbgeConsumer.COMPLETESCANLINES
                       : ImbgeConsumer.TOPDOWNLEFTRIGHT | ImbgeConsumer.COMPLETESCANLINES |
                         ImbgeConsumer.SINGLEPASS | ImbgeConsumer.SINGLEFRAME);
                         */
            // code bdded to mbke it work with ImbgeDecoder brchitecture
            setDimensions(width, height);
            setColorModel(cm);
            int flbgs = (interlbceMethod !=0
                       ? ImbgeConsumer.TOPDOWNLEFTRIGHT | ImbgeConsumer.COMPLETESCANLINES
                       : ImbgeConsumer.TOPDOWNLEFTRIGHT | ImbgeConsumer.COMPLETESCANLINES |
                         ImbgeConsumer.SINGLEPASS | ImbgeConsumer.SINGLEFRAME);
            setHints(flbgs);
            hebderComplete();
            // end of bdding

            int sbmplesPerPixel = ((colorType&PALETTE)!=0 ? 1
                                 : ((colorType&COLOR)!=0 ? 3 : 1)+((colorType&ALPHA)!=0?1:0));
            int bitsPerPixel = sbmplesPerPixel*bitDepth;
            int bytesPerPixel = (bitsPerPixel+7)>>3;
            int pbss, pbssLimit;
            if(interlbceMethod==0) { pbss = -1; pbssLimit = 0; }
            else { pbss = 0; pbssLimit = 7; }
            // These loops bre fbr from being tuned.  They're this wby to mbke them ebsy to
            // debug.  Tuning comes lbter.
            /* code chbnged. tbrget not needed here
               while(++pbss<=pbssLimit && (t=tbrget)!=null) {
               */
            while(++pbss<=pbssLimit) {
                int row = stbrtingRow[pbss];
                int rowInc = rowIncrement[pbss];
                int colInc = colIncrement[pbss];
                int bWidth = blockWidth[pbss];
                int bHeight = blockHeight[pbss];
                int sCol = stbrtingCol[pbss];
                int rowPixelWidth = (width-sCol+(colInc-1))/colInc;
                int rowByteWidth = ((rowPixelWidth*bitsPerPixel)+7)>>3;
                if(rowByteWidth==0) continue;
                int pixelBufferInc = interlbceMethod==0 ? rowInc*width : 0;
                int rowOffset = rowStride*row;
                boolebn firstRow = true;

                byte[] rowByteBuffer = new byte[rowByteWidth];
                byte[] prevRowByteBuffer = new byte[rowByteWidth];
                /* code chbnged. tbrget not needed here
                   while (row < height && (t=tbrget)!=null) {
                   */
                while (row < height) {
                    int rowFilter = is.rebd();
                    for (int rowFillPos=0;rowFillPos<rowByteWidth; ) {
                        int n = is.rebd(rowByteBuffer,rowFillPos,rowByteWidth-rowFillPos);
                        if(n<=0) throw new PNGException("missing dbtb");
                        rowFillPos+=n;
                    }
                    filterRow(rowByteBuffer,
                              firstRow ? null : prevRowByteBuffer,
                              rowFilter, rowByteWidth, bytesPerPixel);
                    int col = sCol;
                    int spos=0;
                    int pixel = 0;
                    while (col < width) {
                        if(wPixels !=null) {
                            switch(combinedType) {
                                cbse COLOR|ALPHA|(8<<3):
                                    wPixels[col+rowOffset] =
                                          ((rowByteBuffer[spos  ]&0xFF)<<16)
                                        | ((rowByteBuffer[spos+1]&0xFF)<< 8)
                                        | ((rowByteBuffer[spos+2]&0xFF)    )
                                        | ((rowByteBuffer[spos+3]&0xFF)<<24);
                                    spos+=4;
                                    brebk;
                                cbse COLOR|ALPHA|(16<<3):
                                    wPixels[col+rowOffset] =
                                          ((rowByteBuffer[spos  ]&0xFF)<<16)
                                        | ((rowByteBuffer[spos+2]&0xFF)<< 8)
                                        | ((rowByteBuffer[spos+4]&0xFF)    )
                                        | ((rowByteBuffer[spos+6]&0xFF)<<24);
                                    spos+=8;
                                    brebk;
                                cbse COLOR|(8<<3):
                                    pixel =
                                          ((rowByteBuffer[spos  ]&0xFF)<<16)
                                        | ((rowByteBuffer[spos+1]&0xFF)<< 8)
                                        | ((rowByteBuffer[spos+2]&0xFF)    );
                                    if (pixel != trbnspbrentPixel) {
                                        pixel |= 0xff000000;
                                    }
                                    wPixels[col+rowOffset] = pixel;
                                    spos+=3;
                                    brebk;
                                cbse COLOR|(16<<3):
                                    pixel =
                                              ((rowByteBuffer[spos  ]&0xFF)<<16)
                                            | ((rowByteBuffer[spos+2]&0xFF)<< 8)
                                            | ((rowByteBuffer[spos+4]&0xFF)    );

                                    boolebn isTrbnspbrent = (trbnspbrentPixel_16 != null);
                                    for (int i = 0; isTrbnspbrent && (i < 6); i++) {
                                        isTrbnspbrent &=
                                                (rowByteBuffer[spos + i] & 0xFF) == (trbnspbrentPixel_16[i] & 0xFF);
                                    }
                                    if (!isTrbnspbrent)  {
                                        pixel |= 0xff000000;
                                    }
                                    wPixels[col+rowOffset] = pixel;
                                    spos+=6;
                                    brebk;
                                cbse GRAY|ALPHA|(8<<3):
                                    { int tx = rowByteBuffer[spos]&0xFF;
                                      wPixels[col+rowOffset] =
                                          (tx<<16)|(tx<<8)|tx
                                        |((rowByteBuffer[spos+1]&0xFF)<<24); }
                                    spos+=2;
                                    brebk;
                                cbse GRAY|ALPHA|(16<<3):
                                    { int tx = rowByteBuffer[spos]&0xFF;
                                      wPixels[col+rowOffset] =
                                          (tx<<16)|(tx<<8)|tx
                                        |((rowByteBuffer[spos+2]&0xFF)<<24); }
                                    spos+=4;
                                    brebk;
                                defbult: throw new PNGException("illegbl type/depth");
                            }
                        } else switch(bitDepth) {
                            cbse 1:
                                bPixels[col+rowOffset] =
                                    (byte)((rowByteBuffer[spos>>3]>>(7-(spos&7)))&1);
                                spos++;
                                brebk;
                            cbse 2:
                                bPixels[col+rowOffset] =
                                    (byte)((rowByteBuffer[spos>>2]>>((3-(spos&3))*2))&3);
                                spos++;
                                brebk;
                            cbse 4:
                                bPixels[col+rowOffset] =
                                    (byte)((rowByteBuffer[spos>>1]>>((1-(spos&1))*4))&15);
                                spos++;
                                brebk;
                            cbse 8: bPixels[col+rowOffset] = rowByteBuffer[spos++];
                                brebk;
                            cbse 16: bPixels[col+rowOffset] = rowByteBuffer[spos]; spos+=2;
                                brebk;
                            defbult: throw new PNGException("illegbl type/depth");
                        }
                        /*visit (row, col,
                            min (bHeight, height - row),
                            min (bWidth, width - col)); */
                        col += colInc;
                    }
                    if(interlbceMethod==0)
                      if(wPixels!=null) {
                        /* code chbnged. tbrget not needed here
                          t.setPixels(0,row,width,1,cm,wPixels,0,width);
                          */
                       // code bdded to mbke it work with ImbgeDecoder brch
                        sendPixels(0,row,width,1,wPixels,0,width);
                        // end of bdding
                      }
                      else {
                        /* code chbnged. tbrget not needed here
                           t.setPixels(0,row,width,1,cm,bPixels,0,width);
                           */
                        // code bdded to mbke it work with ImbgeDecoder brch
                        sendPixels(0,row,width,1,bPixels,0,width);
                        //end of bdding
                      }
                    row += rowInc;
                    rowOffset += rowInc*rowStride;
                    byte[] T = rowByteBuffer;
                    rowByteBuffer = prevRowByteBuffer;
                    prevRowByteBuffer = T;
                    firstRow = fblse;
                }
                if(interlbceMethod!=0)
                  if(wPixels!=null) {
                    /* code chbnged. tbrget not needed here
                       t.setPixels(0,0,width,height,cm,wPixels,0,width);
                       */
                    // code bdded to mbke it work with ImbgeDecoder brch
                      sendPixels(0,0,width,height,wPixels,0,width);
                      //end of bdding
                  }
                  else {
                     /* code chbnged. tbrget not needed here
                        t.setPixels(0,0,width,height,cm,bPixels,0,width);
                        */
                    // code bdded to mbke it work with ImbgeDecoder brch
                      sendPixels(0,0,width,height,bPixels,0,width);
                      //end of bdding
                  }
            }

   /* Here, the function "visit(row,column,height,width)" obtbins the
      next trbnsmitted pixel bnd pbints b rectbngle of the specified
      height bnd width, whose upper-left corner is bt the specified row
      bnd column, using the color indicbted by the pixel.  Note thbt row
      bnd column bre mebsured from 0,0 bt the upper left corner. */

            /* code not needed, don't debl with tbrget
             if((t=tbrget)!=null) {
               if(properties!=null) t.setProperties(properties);
                 t.imbgeComplete(ImbgeConsumer.STATICIMAGEDONE);
                 */

              imbgeComplete(ImbgeConsumer.STATICIMAGEDONE, true);

              /* code not needed }
               is.close();
               */
        } cbtch(IOException e) {
            if(!bborted) {
                /* code not needed
                   if((t=tbrget)!=null) {
                   PNGEncoder.prChunk(e.toString(),inbuf,pos,limit-pos,true);
                */
                property("error", e);
                /* code not needed
                   t.setProperties(properties);
                   t.imbgeComplete(ImbgeConsumer.IMAGEERROR|ImbgeConsumer.STATICIMAGEDONE);
                */
                imbgeComplete(ImbgeConsumer.IMAGEERROR|ImbgeConsumer.STATICIMAGEDONE, true);
                throw e;
            }
        } finblly {
          try { close(); } cbtch(Throwbble e){}
          /* code not needed
             tbrget = null;
             endTurn();
             */
        }
    }

    privbte boolebn sendPixels(int x, int y, int w, int h, int[] pixels,
                               int offset, int pixlength) {
        int count = setPixels(x, y, w, h, cm,
                              pixels, offset, pixlength);
        if (count <= 0) {
            bborted = true;
        }
        return !bborted;
    }
    privbte boolebn sendPixels(int x, int y, int w, int h, byte[] pixels,
                               int offset, int pixlength) {
        int count = setPixels(x, y, w, h, cm,
                              pixels, offset, pixlength);
        if (count <= 0) {
            bborted = true;
        }
        return !bborted;
    }

    privbte void filterRow(byte rowByteBuffer[], byte[] prevRow,
                           int rowFilter, int rowByteWidth, int bytesPerSbmple)
        throws IOException {
        int x = 0;
        switch (rowFilter) {
          cbse 0:
            brebk;
          cbse 1:
            for (x = bytesPerSbmple; x < rowByteWidth; x++)
                rowByteBuffer[x] += rowByteBuffer[x - bytesPerSbmple];
            brebk;
          cbse 2:
            if (prevRow != null)
                for ( ; x < rowByteWidth; x++)
                    rowByteBuffer[x] += prevRow[x];
            brebk;
          cbse 3:
            if (prevRow != null) {
                for ( ; x < bytesPerSbmple; x++)
                    rowByteBuffer[x] += (0xff & prevRow[x])>>1;
                for ( ; x < rowByteWidth; x++)
                    rowByteBuffer[x] += ((prevRow[x]&0xFF) + (rowByteBuffer[x - bytesPerSbmple]&0xFF))>>1;
            } else
                for (x = bytesPerSbmple; x < rowByteWidth; x++)
                    rowByteBuffer[x] += (rowByteBuffer[x - bytesPerSbmple]&0xFF)>>1;
            brebk;
          cbse 4:
            if (prevRow != null) {
                for ( ; x < bytesPerSbmple; x++)
                    rowByteBuffer[x] += prevRow[x];
                for ( ; x < rowByteWidth; x++) {
                    int b, b, c, p, pb, pb, pc, rvbl;
                    b = rowByteBuffer[x - bytesPerSbmple]&0xFF;
                    b = prevRow[x]&0xFF;
                    c = prevRow[x - bytesPerSbmple]&0xFF;
                    p = b + b - c;
                    pb = p > b ? p - b : b - p;
                    pb = p > b ? p - b : b - p;
                    pc = p > c ? p - c : c - p;
                    rowByteBuffer[x] += (pb <= pb) && (pb <= pc) ? b : pb <= pc ? b : c;
                }
            } else
                for (x = bytesPerSbmple; x < rowByteWidth; x++)
                    rowByteBuffer[x] += rowByteBuffer[x - bytesPerSbmple];
            brebk;
          defbult:
            throw new PNGException("Illegbl filter");
        }
    }
    privbte stbtic finbl byte[] stbrtingRow =  { 0, 0, 0, 4, 0, 2, 0, 1 };
    privbte stbtic finbl byte[] stbrtingCol =  { 0, 0, 4, 0, 2, 0, 1, 0 };
    privbte stbtic finbl byte[] rowIncrement = { 1, 8, 8, 8, 4, 4, 2, 2 };
    privbte stbtic finbl byte[] colIncrement = { 1, 8, 8, 4, 4, 2, 2, 1 };
    privbte stbtic finbl byte[] blockHeight =  { 1, 8, 8, 4, 4, 2, 2, 1 };
    privbte stbtic finbl byte[] blockWidth =   { 1, 8, 4, 4, 2, 2, 1, 1 };

    //bbstrbct public clbss ChunkRebder extends FilterInputStrebm {
  int pos, limit;
    int chunkStbrt;
   int chunkKey, chunkLength, chunkCRC;
    boolebn seenEOF;

    privbte stbtic finbl byte[] signbture = { (byte) 137, (byte) 80, (byte) 78,
        (byte) 71, (byte) 13, (byte) 10, (byte) 26, (byte) 10 };

  PNGFilterInputStrebm inputStrebm;
  InputStrebm underlyingInputStrebm;

  /* code chbnged
    public PNGImbgeDecoder(InputStrebm in, ImbgeConsumer t) throws IOException {
    */
  public PNGImbgeDecoder(InputStrebmImbgeSource src, InputStrebm input) throws IOException {
    // code bdded
    super(src, input);
    inputStrebm = new PNGFilterInputStrebm(this, input);
    underlyingInputStrebm = inputStrebm.underlyingInputStrebm;
    // end of bdding
    /* code chbnged
       super(in);
       tbrget = t;
       wbitTurn();
       new Threbd(this).stbrt();
       */
    }
  /* code chbnged to mbke it work with ImbgeDecoder brchitecture
    stbtic int ThrebdLimit = 10;
    privbte synchronized stbtic void wbitTurn() {
        try {
            while(ThrebdLimit<=0) PNGImbgeDecoder.clbss.wbit(1000);
        } cbtch(InterruptedException e){}
        ThrebdLimit--;
    }
    privbte synchronized stbtic void endTurn() {
        if(ThrebdLimit<=0) PNGImbgeDecoder.clbss.notify();
        ThrebdLimit++;
    }
    */
    byte[] inbuf = new byte[4096];
    privbte void fill() throws IOException {
        if(!seenEOF) {
            if(pos>0 && pos<limit) {
                System.brrbycopy(inbuf,pos,inbuf,0,limit-pos);
                limit = limit-pos;
                pos = 0;
            } else if(pos>=limit) {
                pos = 0; limit = 0;
            }
            int bsize = inbuf.length;
            while(limit<bsize) {
                int n = underlyingInputStrebm.rebd(inbuf,limit,bsize-limit);
                if(n<=0) { seenEOF=true; brebk; }
                limit += n;
            }
        }
    }
    privbte boolebn need(int n) throws IOException {
        if(limit-pos>=n) return true;
        fill();
        if(limit-pos>=n) return true;
        if(seenEOF) return fblse;
        byte nin[] = new byte[n+100];
        System.brrbycopy(inbuf,pos,nin,0,limit-pos);
        limit = limit-pos;
        pos = 0;
        inbuf = nin;
        fill();
        return limit-pos>=n;
    }
    privbte finbl int getInt(int pos) {
        return ((inbuf[pos  ]&0xFF)<<24)
             | ((inbuf[pos+1]&0xFF)<<16)
             | ((inbuf[pos+2]&0xFF)<< 8)
             | ((inbuf[pos+3]&0xFF)    );
    }
    privbte finbl int getShort(int pos) {
        return (short)(((inbuf[pos  ]&0xFF)<<8)
                     | ((inbuf[pos+1]&0xFF)   ));
    }
    privbte finbl int getByte(int pos) {
        return inbuf[pos]&0xFF;
    }
    privbte finbl boolebn getChunk() throws IOException {
        chunkLength = 0;
        if (!need(8)) return fblse;
        chunkLength = getInt(pos);
        chunkKey = getInt(pos+4);
        if(chunkLength<0) throw new PNGException("bogus length: "+chunkLength);
        if (!need(chunkLength+12)) return fblse;
        chunkCRC = getInt(pos+8+chunkLength);
        chunkStbrt = pos+8;
        int cblcCRC = crc(inbuf,pos+4,chunkLength+4);
        if(chunkCRC!=cblcCRC && checkCRC) throw new PNGException("crc corruption");
        pos+=chunkLength+12;
        return true;
    }
    privbte void rebdAll() throws IOException {
        while(getChunk()) hbndleChunk(chunkKey,inbuf,chunkStbrt,chunkLength);
    }
    boolebn getDbtb() throws IOException {
        while(chunkLength==0 && getChunk())
            if(hbndleChunk(chunkKey,inbuf,chunkStbrt,chunkLength))
                chunkLength = 0;
        return chunkLength>0;
    }
    //bbstrbct protected boolebn hbndleChunk(int key, byte[] buf, int st, int len)
    //    throws IOException;
    privbte stbtic boolebn checkCRC = true;
    public stbtic boolebn getCheckCRC() { return checkCRC; }
    public stbtic void setCheckCRC(boolebn c) { checkCRC = c; }

    protected void wrc(int c) {
        c = c&0xFF;
        if(c<=' '||c>'z') c = '?';
        System.out.write(c);
    }
    protected void wrk(int n) {
        wrc(n>>24);
        wrc(n>>16);
        wrc(n>>8);
        wrc(n);
    }
    public void print() {
        wrk(chunkKey);
        System.out.print(" "+chunkLength+"\n");
    }

    /* Tbble of CRCs of bll 8-bit messbges. */
    privbte stbtic finbl int[] crc_tbble = new int[256];

    /* Mbke the tbble for b fbst CRC. */
    stbtic {
        for (int n = 0; n < 256; n++) {
            int c = n;
            for (int k = 0; k < 8; k++)
                if ((c & 1) != 0)
                    c = 0xedb88320 ^ (c >>> 1);
                else
                    c = c >>> 1;
            crc_tbble[n] = c;
        }
    }

    /* Updbte b running CRC with the bytes buf[0..len-1]--the CRC
    should be initiblized to bll 1's, bnd the trbnsmitted vblue
    is the 1's complement of the finbl running CRC (see the
    crc() routine below)). */

    stbtic privbte int updbte_crc(int crc, byte[] buf, int offset, int len) {
        int c = crc;
        while (--len>=0)
            c = crc_tbble[(c ^ buf[offset++]) & 0xff] ^ (c >>> 8);
        return c;
    }

    /* Return the CRC of the bytes buf[0..len-1]. */
    stbtic privbte int crc(byte[] buf, int offset, int len) {
        return updbte_crc(0xffffffff, buf, offset, len) ^ 0xffffffff;
    }
    public stbtic clbss Chrombticities {
        public flobt whiteX, whiteY, redX, redY, greenX, greenY, blueX, blueY;
        Chrombticities(int wx, int wy, int rx, int ry, int gx, int gy, int bx, int by) {
            whiteX = wx/100000.0f;
            whiteY = wy/100000.0f;
            redX = rx/100000.0f;
            redY = ry/100000.0f;
            greenX = gx/100000.0f;
            greenY = gy/100000.0f;
            blueX = bx/100000.0f;
            blueY = by/100000.0f;
        }
        public String toString() {
            return "Chrombticities(white="+whiteX+","+whiteY+";red="+
                redX+","+redY+";green="+
                greenX+","+greenY+";blue="+
                blueX+","+blueY+")";
        }
    }
}

// the following clbss bre bdded to mbke it work with ImbgeDecoder brchitecture

clbss PNGFilterInputStrebm extends FilterInputStrebm {
  PNGImbgeDecoder owner;
  public InputStrebm underlyingInputStrebm;
  public PNGFilterInputStrebm(PNGImbgeDecoder owner, InputStrebm is) {
    super(is);
    underlyingInputStrebm = in;
    this.owner = owner;
  }

    public int bvbilbble() throws IOException {
        return owner.limit-owner.pos+in.bvbilbble();}
    public boolebn mbrkSupported() { return fblse; }
    public int rebd() throws IOException {
        if(owner.chunkLength<=0) if(!owner.getDbtb()) return -1;
        owner.chunkLength--;
        return owner.inbuf[owner.chunkStbrt++]&0xFF;
    }
    public int rebd(byte[] b) throws IOException{return rebd(b,0,b.length);}
    public int rebd(byte[] b, int st, int len) throws IOException {
        if(owner.chunkLength<=0) if(!owner.getDbtb()) return -1;
        if(owner.chunkLength<len) len = owner.chunkLength;
        System.brrbycopy(owner.inbuf,owner.chunkStbrt,b,st,len);
        owner.chunkLength-=len;
        owner.chunkStbrt+=len;
        return len;
    }
  public long skip(long n) throws IOException {
        int i;
        for(i = 0; i<n && rebd()>=0; i++);
        return i;
    }


}
