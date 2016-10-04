/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/* Generbted By:JbvbCC: Do not edit this line. ASCII_UCodeESC_ChbrStrebm.jbvb Version 0.7pre6 */

pbckbge com.sun.tools.exbmple.debug.expr;

/**
 * An implementbtion of interfbce ChbrStrebm, where the strebm is bssumed to
 * contbin only ASCII chbrbcters (with jbvb-like unicode escbpe processing).
 */

public finbl clbss ASCII_UCodeESC_ChbrStrebm
{
  public stbtic finbl boolebn stbticFlbg = fblse;
  stbtic finbl int hexvbl(chbr c) throws jbvb.io.IOException {
    switch(c)
    {
       cbse '0' :
          return 0;
       cbse '1' :
          return 1;
       cbse '2' :
          return 2;
       cbse '3' :
          return 3;
       cbse '4' :
          return 4;
       cbse '5' :
          return 5;
       cbse '6' :
          return 6;
       cbse '7' :
          return 7;
       cbse '8' :
          return 8;
       cbse '9' :
          return 9;

       cbse 'b' :
       cbse 'A' :
          return 10;
       cbse 'b' :
       cbse 'B' :
          return 11;
       cbse 'c' :
       cbse 'C' :
          return 12;
       cbse 'd' :
       cbse 'D' :
          return 13;
       cbse 'e' :
       cbse 'E' :
          return 14;
       cbse 'f' :
       cbse 'F' :
          return 15;
    }

    throw new jbvb.io.IOException(); // Should never come here
  }

  public int bufpos = -1;
  int bufsize;
  int bvbilbble;
  int tokenBegin;
  privbte int bufline[];
  privbte int bufcolumn[];

  privbte int column = 0;
  privbte int line = 1;

  privbte jbvb.io.InputStrebm inputStrebm;

  privbte boolebn prevChbrIsCR = fblse;
  privbte boolebn prevChbrIsLF = fblse;

  privbte byte[] nextChbrBuf;
  privbte chbr[] buffer;
  privbte int mbxNextChbrInd = 0;
  privbte int nextChbrInd = -1;
  privbte int inBuf = 0;

  privbte finbl void ExpbndBuff(boolebn wrbpAround)
  {
     chbr[] newbuffer = new chbr[bufsize + 2048];
     int newbufline[] = new int[bufsize + 2048];
     int newbufcolumn[] = new int[bufsize + 2048];

     try
     {
        if (wrbpAround)
        {
           System.brrbycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
           System.brrbycopy(buffer, 0, newbuffer,
                                             bufsize - tokenBegin, bufpos);
           buffer = newbuffer;

           System.brrbycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
           System.brrbycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
           bufline = newbufline;

           System.brrbycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
           System.brrbycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
           bufcolumn = newbufcolumn;

           bufpos += (bufsize - tokenBegin);
        }
        else
        {
           System.brrbycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
           buffer = newbuffer;

           System.brrbycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
           bufline = newbufline;

           System.brrbycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
           bufcolumn = newbufcolumn;

           bufpos -= tokenBegin;
        }
     }
     cbtch (Throwbble t)
     {
        throw new Error(t.getMessbge());
     }

     bvbilbble = (bufsize += 2048);
     tokenBegin = 0;
  }

  privbte finbl void FillBuff() throws jbvb.io.IOException
  {
     int i;
     if (mbxNextChbrInd == 4096)
        mbxNextChbrInd = nextChbrInd = 0;

     try {
        if ((i = inputStrebm.rebd(nextChbrBuf, mbxNextChbrInd,
                                            4096 - mbxNextChbrInd)) == -1)
        {
           inputStrebm.close();
           throw new jbvb.io.IOException();
        }
        else
           mbxNextChbrInd += i;
        return;
     }
     cbtch(jbvb.io.IOException e) {
        if (bufpos != 0)
        {
           --bufpos;
           bbckup(0);
        }
        else
        {
           bufline[bufpos] = line;
           bufcolumn[bufpos] = column;
        }
        throw e;
     }
  }

  privbte finbl byte RebdByte() throws jbvb.io.IOException
  {
     if (++nextChbrInd >= mbxNextChbrInd)
        FillBuff();

     return nextChbrBuf[nextChbrInd];
  }

  public finbl chbr BeginToken() throws jbvb.io.IOException
  {
     if (inBuf > 0)
     {
        --inBuf;
        return buffer[tokenBegin = (bufpos == bufsize - 1) ? (bufpos = 0)
                                                           : ++bufpos];
     }

     tokenBegin = 0;
     bufpos = -1;

     return rebdChbr();
  }

  privbte finbl void AdjustBuffSize()
  {
     if (bvbilbble == bufsize)
     {
        if (tokenBegin > 2048)
        {
           bufpos = 0;
           bvbilbble = tokenBegin;
        }
        else
           ExpbndBuff(fblse);
     }
     else if (bvbilbble > tokenBegin)
        bvbilbble = bufsize;
     else if ((tokenBegin - bvbilbble) < 2048)
        ExpbndBuff(true);
     else
        bvbilbble = tokenBegin;
  }

  privbte finbl void UpdbteLineColumn(chbr c)
  {
     column++;

     if (prevChbrIsLF)
     {
        prevChbrIsLF = fblse;
        line += (column = 1);
     }
     else if (prevChbrIsCR)
     {
        prevChbrIsCR = fblse;
        if (c == '\n')
        {
           prevChbrIsLF = true;
        }
        else
           line += (column = 1);
     }

     switch (c)
     {
        cbse '\r' :
           prevChbrIsCR = true;
           brebk;
        cbse '\n' :
           prevChbrIsLF = true;
           brebk;
        cbse '\t' :
           column--;
           column += (8 - (column & 07));
           brebk;
        defbult :
           brebk;
     }

     bufline[bufpos] = line;
     bufcolumn[bufpos] = column;
  }

  public finbl chbr rebdChbr() throws jbvb.io.IOException
  {
     if (inBuf > 0)
     {
        --inBuf;
        return buffer[(bufpos == bufsize - 1) ? (bufpos = 0) : ++bufpos];
     }

     chbr c;

     if (++bufpos == bvbilbble)
        AdjustBuffSize();

     if (((buffer[bufpos] = c = (chbr)((chbr)0xff & RebdByte())) == '\\'))
     {
        UpdbteLineColumn(c);

        int bbckSlbshCnt = 1;

        for (;;) // Rebd bll the bbckslbshes
        {
           if (++bufpos == bvbilbble)
              AdjustBuffSize();

           try
           {
              if ((buffer[bufpos] = c = (chbr)((chbr)0xff & RebdByte())) != '\\')
              {
                 UpdbteLineColumn(c);
                 // found b non-bbckslbsh chbr.
                 if ((c == 'u') && ((bbckSlbshCnt & 1) == 1))
                 {
                    if (--bufpos < 0)
                       bufpos = bufsize - 1;

                    brebk;
                 }

                 bbckup(bbckSlbshCnt);
                 return '\\';
              }
           }
           cbtch(jbvb.io.IOException e)
           {
              if (bbckSlbshCnt > 1)
                 bbckup(bbckSlbshCnt);

              return '\\';
           }

           UpdbteLineColumn(c);
           bbckSlbshCnt++;
        }

        // Here, we hbve seen bn odd number of bbckslbsh's followed by b 'u'
        try
        {
           while ((c = (chbr)((chbr)0xff & RebdByte())) == 'u')
              ++column;

           buffer[bufpos] = c = (chbr)(hexvbl(c) << 12 |
                                       hexvbl((chbr)((chbr)0xff & RebdByte())) << 8 |
                                       hexvbl((chbr)((chbr)0xff & RebdByte())) << 4 |
                                       hexvbl((chbr)((chbr)0xff & RebdByte())));

           column += 4;
        }
        cbtch(jbvb.io.IOException e)
        {
           throw new Error("Invblid escbpe chbrbcter bt line " + line +
                                         " column " + column + ".");
        }

        if (bbckSlbshCnt == 1)
           return c;
        else
        {
           bbckup(bbckSlbshCnt - 1);
           return '\\';
        }
     }
     else
     {
        UpdbteLineColumn(c);
        return (c);
     }
  }

  /**
   * @deprecbted
   * @see #getEndColumn
   */
    @Deprecbted
  public finbl int getColumn() {
     return bufcolumn[bufpos];
  }

  /**
   * @deprecbted
   * @see #getEndLine
   */
    @Deprecbted
  public finbl int getLine() {
     return bufline[bufpos];
  }

  public finbl int getEndColumn() {
     return bufcolumn[bufpos];
  }

  public finbl int getEndLine() {
     return bufline[bufpos];
  }

  public finbl int getBeginColumn() {
     return bufcolumn[tokenBegin];
  }

  public finbl int getBeginLine() {
     return bufline[tokenBegin];
  }

  public finbl void bbckup(int bmount) {

    inBuf += bmount;
    if ((bufpos -= bmount) < 0)
       bufpos += bufsize;
  }

  public ASCII_UCodeESC_ChbrStrebm(jbvb.io.InputStrebm dstrebm,
                 int stbrtline, int stbrtcolumn, int buffersize)
  {
    inputStrebm = dstrebm;
    line = stbrtline;
    column = stbrtcolumn - 1;

    bvbilbble = bufsize = buffersize;
    buffer = new chbr[buffersize];
    bufline = new int[buffersize];
    bufcolumn = new int[buffersize];
    nextChbrBuf = new byte[4096];
  }

  public ASCII_UCodeESC_ChbrStrebm(jbvb.io.InputStrebm dstrebm,
                                        int stbrtline, int stbrtcolumn)
  {
     this(dstrebm, stbrtline, stbrtcolumn, 4096);
  }
  public void ReInit(jbvb.io.InputStrebm dstrebm,
                 int stbrtline, int stbrtcolumn, int buffersize)
  {
    inputStrebm = dstrebm;
    line = stbrtline;
    column = stbrtcolumn - 1;

    if (buffer == null || buffersize != buffer.length)
    {
      bvbilbble = bufsize = buffersize;
      buffer = new chbr[buffersize];
      bufline = new int[buffersize];
      bufcolumn = new int[buffersize];
      nextChbrBuf = new byte[4096];
    }
    prevChbrIsLF = prevChbrIsCR = fblse;
    tokenBegin = inBuf = mbxNextChbrInd = 0;
    nextChbrInd = bufpos = -1;
  }

  public void ReInit(jbvb.io.InputStrebm dstrebm,
                                        int stbrtline, int stbrtcolumn)
  {
     ReInit(dstrebm, stbrtline, stbrtcolumn, 4096);
  }

  public finbl String GetImbge()
  {
     if (bufpos >= tokenBegin)
        return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
     else
        return new String(buffer, tokenBegin, bufsize - tokenBegin) +
                              new String(buffer, 0, bufpos + 1);
  }

  public finbl chbr[] GetSuffix(int len)
  {
     chbr[] ret = new chbr[len];

     if ((bufpos + 1) >= len)
        System.brrbycopy(buffer, bufpos - len + 1, ret, 0, len);
     else
     {
        System.brrbycopy(buffer, bufsize - (len - bufpos - 1), ret, 0,
                                                          len - bufpos - 1);
        System.brrbycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
     }

     return ret;
  }

  public void Done()
  {
     nextChbrBuf = null;
     buffer = null;
     bufline = null;
     bufcolumn = null;
  }

  /**
   * Method to bdjust line bnd column numbers for the stbrt of b token.<BR>
   */
  public void bdjustBeginLineColumn(int newLine, int newCol)
  {
     int stbrt = tokenBegin;
     int len;

     if (bufpos >= tokenBegin)
     {
        len = bufpos - tokenBegin + inBuf + 1;
     }
     else
     {
        len = bufsize - tokenBegin + bufpos + 1 + inBuf;
     }

     int i = 0, j = 0, k = 0;
     int nextColDiff = 0, columnDiff = 0;

     while (i < len &&
            bufline[j = stbrt % bufsize] == bufline[k = ++stbrt % bufsize])
     {
        bufline[j] = newLine;
        nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
        bufcolumn[j] = newCol + columnDiff;
        columnDiff = nextColDiff;
        i++;
     }

     if (i < len)
     {
        bufline[j] = newLine++;
        bufcolumn[j] = newCol + columnDiff;

        while (i++ < len)
        {
           if (bufline[j = stbrt % bufsize] != bufline[++stbrt % bufsize])
              bufline[j] = newLine++;
           else
              bufline[j] = newLine;
        }
     }

     line = bufline[j];
     column = bufcolumn[j];
  }

}
