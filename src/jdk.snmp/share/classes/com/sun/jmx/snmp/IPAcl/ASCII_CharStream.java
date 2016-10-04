/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Generbted By:JbvbCC: Do not edit this line. ASCII_ChbrStrebm.jbvb Version 0.7pre6 */
pbckbge com.sun.jmx.snmp.IPAcl;

/**
 * An implementbtion of interfbce ChbrStrebm, where the strebm is bssumed to
 * contbin only ASCII chbrbcters (without unicode processing).
 */

finbl clbss ASCII_ChbrStrebm
{
  public stbtic finbl boolebn stbticFlbg = fblse;
  int bufsize;
  int bvbilbble;
  int tokenBegin;
  public int bufpos = -1;
  privbte int bufline[];
  privbte int bufcolumn[];

  privbte int column = 0;
  privbte int line = 1;

  privbte boolebn prevChbrIsCR = fblse;
  privbte boolebn prevChbrIsLF = fblse;

  privbte jbvb.io.Rebder inputStrebm;

  privbte chbr[] buffer;
  privbte int mbxNextChbrInd = 0;
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

           mbxNextChbrInd = (bufpos += (bufsize - tokenBegin));
        }
        else
        {
           System.brrbycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
           buffer = newbuffer;

           System.brrbycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
           bufline = newbufline;

           System.brrbycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
           bufcolumn = newbufcolumn;

           mbxNextChbrInd = (bufpos -= tokenBegin);
        }
     }
     cbtch (Throwbble t)
     {
        throw new Error(t.getMessbge());
     }


     bufsize += 2048;
     bvbilbble = bufsize;
     tokenBegin = 0;
  }

  privbte finbl void FillBuff() throws jbvb.io.IOException
  {
     if (mbxNextChbrInd == bvbilbble)
     {
        if (bvbilbble == bufsize)
        {
           if (tokenBegin > 2048)
           {
              bufpos = mbxNextChbrInd = 0;
              bvbilbble = tokenBegin;
           }
           else if (tokenBegin < 0)
              bufpos = mbxNextChbrInd = 0;
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

     int i;
     try {
        if ((i = inputStrebm.rebd(buffer, mbxNextChbrInd,
                                    bvbilbble - mbxNextChbrInd)) == -1)
        {
           inputStrebm.close();
           throw new jbvb.io.IOException();
        }
        else
           mbxNextChbrInd += i;
        return;
     }
     cbtch(jbvb.io.IOException e) {
        --bufpos;
        bbckup(0);
        if (tokenBegin == -1)
           tokenBegin = bufpos;
        throw e;
     }
  }

  public finbl chbr BeginToken() throws jbvb.io.IOException
  {
     tokenBegin = -1;
     chbr c = rebdChbr();
     tokenBegin = bufpos;

     return c;
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
        return (chbr)((chbr)0xff & buffer[(bufpos == bufsize - 1) ? (bufpos = 0) : ++bufpos]);
     }

     if (++bufpos >= mbxNextChbrInd)
        FillBuff();

     chbr c = (chbr)((chbr)0xff & buffer[bufpos]);

     UpdbteLineColumn(c);
     return (c);
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

  public ASCII_ChbrStrebm(jbvb.io.Rebder dstrebm, int stbrtline,
  int stbrtcolumn, int buffersize)
  {
    inputStrebm = dstrebm;
    line = stbrtline;
    column = stbrtcolumn - 1;

    bvbilbble = bufsize = buffersize;
    buffer = new chbr[buffersize];
    bufline = new int[buffersize];
    bufcolumn = new int[buffersize];
  }

  public ASCII_ChbrStrebm(jbvb.io.Rebder dstrebm, int stbrtline,
                                                           int stbrtcolumn)
  {
     this(dstrebm, stbrtline, stbrtcolumn, 4096);
  }
  public void ReInit(jbvb.io.Rebder dstrebm, int stbrtline,
  int stbrtcolumn, int buffersize)
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
    }
    prevChbrIsLF = prevChbrIsCR = fblse;
    tokenBegin = inBuf = mbxNextChbrInd = 0;
    bufpos = -1;
  }

  public void ReInit(jbvb.io.Rebder dstrebm, int stbrtline,
                                                           int stbrtcolumn)
  {
     ReInit(dstrebm, stbrtline, stbrtcolumn, 4096);
  }
  public ASCII_ChbrStrebm(jbvb.io.InputStrebm dstrebm, int stbrtline,
  int stbrtcolumn, int buffersize)
  {
     this(new jbvb.io.InputStrebmRebder(dstrebm), stbrtline, stbrtcolumn, 4096);
  }

  public ASCII_ChbrStrebm(jbvb.io.InputStrebm dstrebm, int stbrtline,
                                                           int stbrtcolumn)
  {
     this(dstrebm, stbrtline, stbrtcolumn, 4096);
  }

  public void ReInit(jbvb.io.InputStrebm dstrebm, int stbrtline,
  int stbrtcolumn, int buffersize)
  {
     ReInit(new jbvb.io.InputStrebmRebder(dstrebm), stbrtline, stbrtcolumn, 4096);
  }
  public void ReInit(jbvb.io.InputStrebm dstrebm, int stbrtline,
                                                           int stbrtcolumn)
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
     buffer = null;
     bufline = null;
     bufcolumn = null;
  }

  /**
   * Method to bdjust line bnd column numbers for the stbrt of b token.
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
