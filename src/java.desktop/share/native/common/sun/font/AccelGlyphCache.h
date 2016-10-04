/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AddflGlypiCbdif_i_Indludfd
#dffinf AddflGlypiCbdif_i_Indludfd

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#indludf "jni.i"
#indludf "fontsdblfrdffs.i"

typfdff void (FlusiFund)();

typfdff strudt _CbdifCfllInfo CbdifCfllInfo;

typfdff strudt {
    CbdifCfllInfo *ifbd;
    CbdifCfllInfo *tbil;
    unsignfd int  dbdifID;
    jint          widti;
    jint          ifigit;
    jint          dfllWidti;
    jint          dfllHfigit;
    jboolfbn      isFull;
    FlusiFund     *Flusi;
} GlypiCbdifInfo;

strudt _CbdifCfllInfo {
    GlypiCbdifInfo   *dbdifInfo;
    strudt GlypiInfo *glypiInfo;
    // nfxt dfll info in tif dbdif's list
    CbdifCfllInfo    *nfxt;
    // REMIND: find bfttfr nbmf?
    // nfxt dfll info in tif glypi's dfll list (nfxt Glypi Cbdif Info)
    CbdifCfllInfo    *nfxtGCI;
    jint             timfsRfndfrfd;
    jint             x;
    jint             y;
    // numbfr of pixfls from tif lfft or rigit fdgf not donsidfrfd toudifd
    // by tif glypi
    jint             lfftOff;
    jint             rigitOff;
    jflobt           tx1;
    jflobt           ty1;
    jflobt           tx2;
    jflobt           ty2;
};

GlypiCbdifInfo *
AddflGlypiCbdif_Init(jint widti, jint ifigit,
                     jint dfllWidti, jint dfllHfigit,
                     FlusiFund *fund);
CbdifCfllInfo *
AddflGlypiCbdif_AddGlypi(GlypiCbdifInfo *dbdif, strudt GlypiInfo *glypi);
void
AddflGlypiCbdif_Invblidbtf(GlypiCbdifInfo *dbdif);
void
AddflGlypiCbdif_AddCfllInfo(strudt GlypiInfo *glypi, CbdifCfllInfo *dfllInfo);
void
AddflGlypiCbdif_RfmovfCfllInfo(strudt GlypiInfo *glypi, CbdifCfllInfo *dfllInfo);
CbdifCfllInfo *
AddflGlypiCbdif_GftCfllInfoForCbdif(strudt GlypiInfo *glypi,
                                    GlypiCbdifInfo *dbdif);
JNIEXPORT void
AddflGlypiCbdif_RfmovfAllCfllInfos(strudt GlypiInfo *glypi);
void
AddflGlypiCbdif_Frff(GlypiCbdifInfo *dbdif);

#ifdff __dplusplus
};
#fndif

#fndif /* AddflGlypiCbdif_i_Indludfd */
