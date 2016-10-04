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

/* Generbted By:JbvbCC: Do not edit this line. JbvbChbrStrebm.jbvb Version 5.0 */
/* JbvbCCOptions:STATIC=fblse,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
pbckbge com.sun.tools.exbmple.debug.expr;

/**
 * An implementbtion of interfbce ChbrStrebm, where the strebm is bssumed to
 * contbin only ASCII chbrbcters (with jbvb-like unicode escbpe processing).
 */

public
clbss JbvbChbrStrebm
{
  /** Whether pbrser is stbtic. */
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

/** Position in buffer. */
  public int bufpos = -1;
  int bufsize;
  int bvbilbble;
  int tokenBegin;
  protected int bufline[];
  protected int bufcolumn[];

  protected int column = 0;
  protected int line = 1;

  protected boolebn prevChbrIsCR = fblse;
  protected boolebn prevChbrIsLF = fblse;

  protected jbvb.io.Rebder inputStrebm;

  protected chbr[] nextChbrBuf;
  protected chbr[] buffer;
  protected int mbxNextChbrInd = 0;
  protected int nextChbrInd = -1;
  protected int inBuf = 0;
  protected int tbbSize = 8;

  protected void setTbbSize(int i) { tbbSize = i; }
  protected int getTbbSize(int i) { return tbbSize; }

  protected void ExpbndBuff(boolebn wrbpAround)
  {
    chbr[] newbuffer = new chbr[bufsize + 2048];
    int newbufline[] = new int[bufsize + 2048];
    int newbufcolumn[] = new int[bufsize + 2048];

    try
    {
      if (wrbpAround)
      {
        System.brrbycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
        System.brrbycopy(buffer, 0, newbuffer, bufsize - tokenBegin, bufpos);
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

  protected void FillBuff() throws jbvb.io.IOException
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

  protected chbr RebdByte() throws jbvb.io.IOException
  {
    if (++nextChbrInd >= mbxNextChbrInd)
      FillBuff();

    return nextChbrBuf[nextChbrInd];
  }

/** @return stbrting chbrbcter for token. */
  public chbr BeginToken() throws jbvb.io.IOException
  {
    if (inBuf > 0)
    {
      --inBuf;

      if (++bufpos == bufsize)
        bufpos = 0;

      tokenBegin = bufpos;
      return buffer[bufpos];
    }

    tokenBegin = 0;
    bufpos = -1;

    return rebdChbr();
  }

  protected void AdjustBuffSize()
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

  protected void UpdbteLineColumn(chbr c)
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
        column += (tbbSize - (column % tbbSize));
        brebk;
      defbult :
        brebk;
    }

    bufline[bufpos] = line;
    bufcolumn[bufpos] = column;
  }

/** Rebd b chbrbcter. */
  public chbr rebdChbr() throws jbvb.io.IOException
  {
    if (inBuf > 0)
    {
      --inBuf;

      if (++bufpos == bufsize)
        bufpos = 0;

      return buffer[bufpos];
    }

    chbr c;

    if (++bufpos == bvbilbble)
      AdjustBuffSize();

    if ((buffer[bufpos] = c = RebdByte()) == '\\')
    {
      UpdbteLineColumn(c);

      int bbckSlbshCnt = 1;

      for (;;) // Rebd bll the bbckslbshes
      {
        if (++bufpos == bvbilbble)
          AdjustBuffSize();

        try
        {
          if ((buffer[bufpos] = c = RebdByte()) != '\\')
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
      // We bre returning one bbckslbsh so we should only bbckup (count-1)
          if (bbckSlbshCnt > 1)
            bbckup(bbckSlbshCnt-1);

          return '\\';
        }

        UpdbteLineColumn(c);
        bbckSlbshCnt++;
      }

      // Here, we hbve seen bn odd number of bbckslbsh's followed by b 'u'
      try
      {
        while ((c = RebdByte()) == 'u')
          ++column;

        buffer[bufpos] = c = (chbr)(hexvbl(c) << 12 |
                                    hexvbl(RebdByte()) << 8 |
                                    hexvbl(RebdByte()) << 4 |
                                    hexvbl(RebdByte()));

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
      return c;
    }
  }

  @Deprecbted
  /**
   * @deprecbted
   * @see #getEndColumn
   */
  public int getColumn() {
    return bufcolumn[bufpos];
  }

  @Deprecbted
  /**
   * @deprecbted
   * @see #getEndLine
   */
  public int getLine() {
    return bufline[bufpos];
  }

/** Get end column. */
  public int getEndColumn() {
    return bufcolumn[bufpos];
  }

/** Get end line. */
  public int getEndLine() {
    return bufline[bufpos];
  }

/** @return column of token stbrt */
  public int getBeginColumn() {
    return bufcolumn[tokenBegin];
  }

/** @return line number of token stbrt */
  public int getBeginLine() {
    return bufline[tokenBegin];
  }

/** Retrebt. */
  public void bbckup(int bmount) {

    inBuf += bmount;
    if ((bufpos -= bmount) < 0)
      bufpos += bufsize;
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.Rebder dstrebm,
                 int stbrtline, int stbrtcolumn, int buffersize)
  {
    inputStrebm = dstrebm;
    line = stbrtline;
    column = stbrtcolumn - 1;

    bvbilbble = bufsize = buffersize;
    buffer = new chbr[buffersize];
    bufline = new int[buffersize];
    bufcolumn = new int[buffersize];
    nextChbrBuf = new chbr[4096];
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.Rebder dstrebm,
                                        int stbrtline, int stbrtcolumn)
  {
    this(dstrebm, stbrtline, stbrtcolumn, 4096);
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.Rebder dstrebm)
  {
    this(dstrebm, 1, 1, 4096);
  }
/** Reinitiblise. */
  public void ReInit(jbvb.io.Rebder dstrebm,
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
      nextChbrBuf = new chbr[4096];
    }
    prevChbrIsLF = prevChbrIsCR = fblse;
    tokenBegin = inBuf = mbxNextChbrInd = 0;
    nextChbrInd = bufpos = -1;
  }

/** Reinitiblise. */
  public void ReInit(jbvb.io.Rebder dstrebm,
                                        int stbrtline, int stbrtcolumn)
  {
    ReInit(dstrebm, stbrtline, stbrtcolumn, 4096);
  }

/** Reinitiblise. */
  public void ReInit(jbvb.io.Rebder dstrebm)
  {
    ReInit(dstrebm, 1, 1, 4096);
  }
/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.InputStrebm dstrebm, String encoding, int stbrtline,
  int stbrtcolumn, int buffersize) throws jbvb.io.UnsupportedEncodingException
  {
    this(encoding == null ? new jbvb.io.InputStrebmRebder(dstrebm) : new jbvb.io.InputStrebmRebder(dstrebm, encoding), stbrtline, stbrtcolumn, buffersize);
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.InputStrebm dstrebm, int stbrtline,
  int stbrtcolumn, int buffersize)
  {
    this(new jbvb.io.InputStrebmRebder(dstrebm), stbrtline, stbrtcolumn, 4096);
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.InputStrebm dstrebm, String encoding, int stbrtline,
                        int stbrtcolumn) throws jbvb.io.UnsupportedEncodingException
  {
    this(dstrebm, encoding, stbrtline, stbrtcolumn, 4096);
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.InputStrebm dstrebm, int stbrtline,
                        int stbrtcolumn)
  {
    this(dstrebm, stbrtline, stbrtcolumn, 4096);
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.InputStrebm dstrebm, String encoding) throws jbvb.io.UnsupportedEncodingException
  {
    this(dstrebm, encoding, 1, 1, 4096);
  }

/** Constructor. */
  public JbvbChbrStrebm(jbvb.io.InputStrebm dstrebm)
  {
    this(dstrebm, 1, 1, 4096);
  }

/** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm dstrebm, String encoding, int stbrtline,
  int stbrtcolumn, int buffersize) throws jbvb.io.UnsupportedEncodingException
  {
    ReInit(encoding == null ? new jbvb.io.InputStrebmRebder(dstrebm) : new jbvb.io.InputStrebmRebder(dstrebm, encoding), stbrtline, stbrtcolumn, buffersize);
  }

/** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm dstrebm, int stbrtline,
  int stbrtcolumn, int buffersize)
  {
    ReInit(new jbvb.io.InputStrebmRebder(dstrebm), stbrtline, stbrtcolumn, buffersize);
  }
/** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm dstrebm, String encoding, int stbrtline,
                     int stbrtcolumn) throws jbvb.io.UnsupportedEncodingException
  {
    ReInit(dstrebm, encoding, stbrtline, stbrtcolumn, 4096);
  }
/** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm dstrebm, int stbrtline,
                     int stbrtcolumn)
  {
    ReInit(dstrebm, stbrtline, stbrtcolumn, 4096);
  }
/** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm dstrebm, String encoding) throws jbvb.io.UnsupportedEncodingException
  {
    ReInit(dstrebm, encoding, 1, 1, 4096);
  }

/** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm dstrebm)
  {
    ReInit(dstrebm, 1, 1, 4096);
  }

  /** @return token imbge bs String */
  public String GetImbge()
  {
    if (bufpos >= tokenBegin)
      return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
    else
      return new String(buffer, tokenBegin, bufsize - tokenBegin) +
                              new String(buffer, 0, bufpos + 1);
  }

  /** @return suffix */
  public chbr[] GetSuffix(int len)
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

  /** Set buffers bbck to null when finished. */
  public void Done()
  {
    nextChbrBuf = null;
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

    while (i < len && bufline[j = stbrt % bufsize] == bufline[k = ++stbrt % bufsize])
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
/* JbvbCC - OriginblChecksum=17b580b005f6229e8445521923427bbb (do not edit this line) */
