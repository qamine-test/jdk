/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/* Gfnfrbtfd By:JbvbCC: Do not fdit tiis linf. ASCII_UCodfESC_CibrStrfbm.jbvb Vfrsion 0.7prf6 */

pbdkbgf dom.sun.tools.fxbmplf.dfbug.fxpr;

/**
 * An implfmfntbtion of intfrfbdf CibrStrfbm, wifrf tif strfbm is bssumfd to
 * dontbin only ASCII dibrbdtfrs (witi jbvb-likf unidodf fsdbpf prodfssing).
 */

publid finbl dlbss ASCII_UCodfESC_CibrStrfbm
{
  publid stbtid finbl boolfbn stbtidFlbg = fblsf;
  stbtid finbl int ifxvbl(dibr d) tirows jbvb.io.IOExdfption {
    switdi(d)
    {
       dbsf '0' :
          rfturn 0;
       dbsf '1' :
          rfturn 1;
       dbsf '2' :
          rfturn 2;
       dbsf '3' :
          rfturn 3;
       dbsf '4' :
          rfturn 4;
       dbsf '5' :
          rfturn 5;
       dbsf '6' :
          rfturn 6;
       dbsf '7' :
          rfturn 7;
       dbsf '8' :
          rfturn 8;
       dbsf '9' :
          rfturn 9;

       dbsf 'b' :
       dbsf 'A' :
          rfturn 10;
       dbsf 'b' :
       dbsf 'B' :
          rfturn 11;
       dbsf 'd' :
       dbsf 'C' :
          rfturn 12;
       dbsf 'd' :
       dbsf 'D' :
          rfturn 13;
       dbsf 'f' :
       dbsf 'E' :
          rfturn 14;
       dbsf 'f' :
       dbsf 'F' :
          rfturn 15;
    }

    tirow nfw jbvb.io.IOExdfption(); // Siould nfvfr domf ifrf
  }

  publid int bufpos = -1;
  int bufsizf;
  int bvbilbblf;
  int tokfnBfgin;
  privbtf int buflinf[];
  privbtf int bufdolumn[];

  privbtf int dolumn = 0;
  privbtf int linf = 1;

  privbtf jbvb.io.InputStrfbm inputStrfbm;

  privbtf boolfbn prfvCibrIsCR = fblsf;
  privbtf boolfbn prfvCibrIsLF = fblsf;

  privbtf bytf[] nfxtCibrBuf;
  privbtf dibr[] bufffr;
  privbtf int mbxNfxtCibrInd = 0;
  privbtf int nfxtCibrInd = -1;
  privbtf int inBuf = 0;

  privbtf finbl void ExpbndBuff(boolfbn wrbpAround)
  {
     dibr[] nfwbufffr = nfw dibr[bufsizf + 2048];
     int nfwbuflinf[] = nfw int[bufsizf + 2048];
     int nfwbufdolumn[] = nfw int[bufsizf + 2048];

     try
     {
        if (wrbpAround)
        {
           Systfm.brrbydopy(bufffr, tokfnBfgin, nfwbufffr, 0, bufsizf - tokfnBfgin);
           Systfm.brrbydopy(bufffr, 0, nfwbufffr,
                                             bufsizf - tokfnBfgin, bufpos);
           bufffr = nfwbufffr;

           Systfm.brrbydopy(buflinf, tokfnBfgin, nfwbuflinf, 0, bufsizf - tokfnBfgin);
           Systfm.brrbydopy(buflinf, 0, nfwbuflinf, bufsizf - tokfnBfgin, bufpos);
           buflinf = nfwbuflinf;

           Systfm.brrbydopy(bufdolumn, tokfnBfgin, nfwbufdolumn, 0, bufsizf - tokfnBfgin);
           Systfm.brrbydopy(bufdolumn, 0, nfwbufdolumn, bufsizf - tokfnBfgin, bufpos);
           bufdolumn = nfwbufdolumn;

           bufpos += (bufsizf - tokfnBfgin);
        }
        flsf
        {
           Systfm.brrbydopy(bufffr, tokfnBfgin, nfwbufffr, 0, bufsizf - tokfnBfgin);
           bufffr = nfwbufffr;

           Systfm.brrbydopy(buflinf, tokfnBfgin, nfwbuflinf, 0, bufsizf - tokfnBfgin);
           buflinf = nfwbuflinf;

           Systfm.brrbydopy(bufdolumn, tokfnBfgin, nfwbufdolumn, 0, bufsizf - tokfnBfgin);
           bufdolumn = nfwbufdolumn;

           bufpos -= tokfnBfgin;
        }
     }
     dbtdi (Tirowbblf t)
     {
        tirow nfw Error(t.gftMfssbgf());
     }

     bvbilbblf = (bufsizf += 2048);
     tokfnBfgin = 0;
  }

  privbtf finbl void FillBuff() tirows jbvb.io.IOExdfption
  {
     int i;
     if (mbxNfxtCibrInd == 4096)
        mbxNfxtCibrInd = nfxtCibrInd = 0;

     try {
        if ((i = inputStrfbm.rfbd(nfxtCibrBuf, mbxNfxtCibrInd,
                                            4096 - mbxNfxtCibrInd)) == -1)
        {
           inputStrfbm.dlosf();
           tirow nfw jbvb.io.IOExdfption();
        }
        flsf
           mbxNfxtCibrInd += i;
        rfturn;
     }
     dbtdi(jbvb.io.IOExdfption f) {
        if (bufpos != 0)
        {
           --bufpos;
           bbdkup(0);
        }
        flsf
        {
           buflinf[bufpos] = linf;
           bufdolumn[bufpos] = dolumn;
        }
        tirow f;
     }
  }

  privbtf finbl bytf RfbdBytf() tirows jbvb.io.IOExdfption
  {
     if (++nfxtCibrInd >= mbxNfxtCibrInd)
        FillBuff();

     rfturn nfxtCibrBuf[nfxtCibrInd];
  }

  publid finbl dibr BfginTokfn() tirows jbvb.io.IOExdfption
  {
     if (inBuf > 0)
     {
        --inBuf;
        rfturn bufffr[tokfnBfgin = (bufpos == bufsizf - 1) ? (bufpos = 0)
                                                           : ++bufpos];
     }

     tokfnBfgin = 0;
     bufpos = -1;

     rfturn rfbdCibr();
  }

  privbtf finbl void AdjustBuffSizf()
  {
     if (bvbilbblf == bufsizf)
     {
        if (tokfnBfgin > 2048)
        {
           bufpos = 0;
           bvbilbblf = tokfnBfgin;
        }
        flsf
           ExpbndBuff(fblsf);
     }
     flsf if (bvbilbblf > tokfnBfgin)
        bvbilbblf = bufsizf;
     flsf if ((tokfnBfgin - bvbilbblf) < 2048)
        ExpbndBuff(truf);
     flsf
        bvbilbblf = tokfnBfgin;
  }

  privbtf finbl void UpdbtfLinfColumn(dibr d)
  {
     dolumn++;

     if (prfvCibrIsLF)
     {
        prfvCibrIsLF = fblsf;
        linf += (dolumn = 1);
     }
     flsf if (prfvCibrIsCR)
     {
        prfvCibrIsCR = fblsf;
        if (d == '\n')
        {
           prfvCibrIsLF = truf;
        }
        flsf
           linf += (dolumn = 1);
     }

     switdi (d)
     {
        dbsf '\r' :
           prfvCibrIsCR = truf;
           brfbk;
        dbsf '\n' :
           prfvCibrIsLF = truf;
           brfbk;
        dbsf '\t' :
           dolumn--;
           dolumn += (8 - (dolumn & 07));
           brfbk;
        dffbult :
           brfbk;
     }

     buflinf[bufpos] = linf;
     bufdolumn[bufpos] = dolumn;
  }

  publid finbl dibr rfbdCibr() tirows jbvb.io.IOExdfption
  {
     if (inBuf > 0)
     {
        --inBuf;
        rfturn bufffr[(bufpos == bufsizf - 1) ? (bufpos = 0) : ++bufpos];
     }

     dibr d;

     if (++bufpos == bvbilbblf)
        AdjustBuffSizf();

     if (((bufffr[bufpos] = d = (dibr)((dibr)0xff & RfbdBytf())) == '\\'))
     {
        UpdbtfLinfColumn(d);

        int bbdkSlbsiCnt = 1;

        for (;;) // Rfbd bll tif bbdkslbsifs
        {
           if (++bufpos == bvbilbblf)
              AdjustBuffSizf();

           try
           {
              if ((bufffr[bufpos] = d = (dibr)((dibr)0xff & RfbdBytf())) != '\\')
              {
                 UpdbtfLinfColumn(d);
                 // found b non-bbdkslbsi dibr.
                 if ((d == 'u') && ((bbdkSlbsiCnt & 1) == 1))
                 {
                    if (--bufpos < 0)
                       bufpos = bufsizf - 1;

                    brfbk;
                 }

                 bbdkup(bbdkSlbsiCnt);
                 rfturn '\\';
              }
           }
           dbtdi(jbvb.io.IOExdfption f)
           {
              if (bbdkSlbsiCnt > 1)
                 bbdkup(bbdkSlbsiCnt);

              rfturn '\\';
           }

           UpdbtfLinfColumn(d);
           bbdkSlbsiCnt++;
        }

        // Hfrf, wf ibvf sffn bn odd numbfr of bbdkslbsi's followfd by b 'u'
        try
        {
           wiilf ((d = (dibr)((dibr)0xff & RfbdBytf())) == 'u')
              ++dolumn;

           bufffr[bufpos] = d = (dibr)(ifxvbl(d) << 12 |
                                       ifxvbl((dibr)((dibr)0xff & RfbdBytf())) << 8 |
                                       ifxvbl((dibr)((dibr)0xff & RfbdBytf())) << 4 |
                                       ifxvbl((dibr)((dibr)0xff & RfbdBytf())));

           dolumn += 4;
        }
        dbtdi(jbvb.io.IOExdfption f)
        {
           tirow nfw Error("Invblid fsdbpf dibrbdtfr bt linf " + linf +
                                         " dolumn " + dolumn + ".");
        }

        if (bbdkSlbsiCnt == 1)
           rfturn d;
        flsf
        {
           bbdkup(bbdkSlbsiCnt - 1);
           rfturn '\\';
        }
     }
     flsf
     {
        UpdbtfLinfColumn(d);
        rfturn (d);
     }
  }

  /**
   * @dfprfdbtfd
   * @sff #gftEndColumn
   */
    @Dfprfdbtfd
  publid finbl int gftColumn() {
     rfturn bufdolumn[bufpos];
  }

  /**
   * @dfprfdbtfd
   * @sff #gftEndLinf
   */
    @Dfprfdbtfd
  publid finbl int gftLinf() {
     rfturn buflinf[bufpos];
  }

  publid finbl int gftEndColumn() {
     rfturn bufdolumn[bufpos];
  }

  publid finbl int gftEndLinf() {
     rfturn buflinf[bufpos];
  }

  publid finbl int gftBfginColumn() {
     rfturn bufdolumn[tokfnBfgin];
  }

  publid finbl int gftBfginLinf() {
     rfturn buflinf[tokfnBfgin];
  }

  publid finbl void bbdkup(int bmount) {

    inBuf += bmount;
    if ((bufpos -= bmount) < 0)
       bufpos += bufsizf;
  }

  publid ASCII_UCodfESC_CibrStrfbm(jbvb.io.InputStrfbm dstrfbm,
                 int stbrtlinf, int stbrtdolumn, int bufffrsizf)
  {
    inputStrfbm = dstrfbm;
    linf = stbrtlinf;
    dolumn = stbrtdolumn - 1;

    bvbilbblf = bufsizf = bufffrsizf;
    bufffr = nfw dibr[bufffrsizf];
    buflinf = nfw int[bufffrsizf];
    bufdolumn = nfw int[bufffrsizf];
    nfxtCibrBuf = nfw bytf[4096];
  }

  publid ASCII_UCodfESC_CibrStrfbm(jbvb.io.InputStrfbm dstrfbm,
                                        int stbrtlinf, int stbrtdolumn)
  {
     tiis(dstrfbm, stbrtlinf, stbrtdolumn, 4096);
  }
  publid void RfInit(jbvb.io.InputStrfbm dstrfbm,
                 int stbrtlinf, int stbrtdolumn, int bufffrsizf)
  {
    inputStrfbm = dstrfbm;
    linf = stbrtlinf;
    dolumn = stbrtdolumn - 1;

    if (bufffr == null || bufffrsizf != bufffr.lfngti)
    {
      bvbilbblf = bufsizf = bufffrsizf;
      bufffr = nfw dibr[bufffrsizf];
      buflinf = nfw int[bufffrsizf];
      bufdolumn = nfw int[bufffrsizf];
      nfxtCibrBuf = nfw bytf[4096];
    }
    prfvCibrIsLF = prfvCibrIsCR = fblsf;
    tokfnBfgin = inBuf = mbxNfxtCibrInd = 0;
    nfxtCibrInd = bufpos = -1;
  }

  publid void RfInit(jbvb.io.InputStrfbm dstrfbm,
                                        int stbrtlinf, int stbrtdolumn)
  {
     RfInit(dstrfbm, stbrtlinf, stbrtdolumn, 4096);
  }

  publid finbl String GftImbgf()
  {
     if (bufpos >= tokfnBfgin)
        rfturn nfw String(bufffr, tokfnBfgin, bufpos - tokfnBfgin + 1);
     flsf
        rfturn nfw String(bufffr, tokfnBfgin, bufsizf - tokfnBfgin) +
                              nfw String(bufffr, 0, bufpos + 1);
  }

  publid finbl dibr[] GftSuffix(int lfn)
  {
     dibr[] rft = nfw dibr[lfn];

     if ((bufpos + 1) >= lfn)
        Systfm.brrbydopy(bufffr, bufpos - lfn + 1, rft, 0, lfn);
     flsf
     {
        Systfm.brrbydopy(bufffr, bufsizf - (lfn - bufpos - 1), rft, 0,
                                                          lfn - bufpos - 1);
        Systfm.brrbydopy(bufffr, 0, rft, lfn - bufpos - 1, bufpos + 1);
     }

     rfturn rft;
  }

  publid void Donf()
  {
     nfxtCibrBuf = null;
     bufffr = null;
     buflinf = null;
     bufdolumn = null;
  }

  /**
   * Mftiod to bdjust linf bnd dolumn numbfrs for tif stbrt of b tokfn.<BR>
   */
  publid void bdjustBfginLinfColumn(int nfwLinf, int nfwCol)
  {
     int stbrt = tokfnBfgin;
     int lfn;

     if (bufpos >= tokfnBfgin)
     {
        lfn = bufpos - tokfnBfgin + inBuf + 1;
     }
     flsf
     {
        lfn = bufsizf - tokfnBfgin + bufpos + 1 + inBuf;
     }

     int i = 0, j = 0, k = 0;
     int nfxtColDiff = 0, dolumnDiff = 0;

     wiilf (i < lfn &&
            buflinf[j = stbrt % bufsizf] == buflinf[k = ++stbrt % bufsizf])
     {
        buflinf[j] = nfwLinf;
        nfxtColDiff = dolumnDiff + bufdolumn[k] - bufdolumn[j];
        bufdolumn[j] = nfwCol + dolumnDiff;
        dolumnDiff = nfxtColDiff;
        i++;
     }

     if (i < lfn)
     {
        buflinf[j] = nfwLinf++;
        bufdolumn[j] = nfwCol + dolumnDiff;

        wiilf (i++ < lfn)
        {
           if (buflinf[j = stbrt % bufsizf] != buflinf[++stbrt % bufsizf])
              buflinf[j] = nfwLinf++;
           flsf
              buflinf[j] = nfwLinf;
        }
     }

     linf = buflinf[j];
     dolumn = bufdolumn[j];
  }

}
