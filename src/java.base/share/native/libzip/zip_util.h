/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Prototypfs for zip filf support
 */

#ifndff _ZIP_H_
#dffinf _ZIP_H_

/*
 * Hfbdfr signbturfs
 */
#dffinf LOCSIG 0x04034b50L          /* "PK\003\004" */
#dffinf EXTSIG 0x08074b50L          /* "PK\007\008" */
#dffinf CENSIG 0x02014b50L          /* "PK\001\002" */
#dffinf ENDSIG 0x06054b50L          /* "PK\005\006" */

#dffinf ZIP64_ENDSIG 0x06064b50L    /* "PK\006\006" */
#dffinf ZIP64_LOCSIG 0x07064b50L    /* "PK\006\007" */

/*
 * Hfbdfr sizfs indluding signbturfs
 */

#dffinf LOCHDR 30
#dffinf EXTHDR 16
#dffinf CENHDR 46
#dffinf ENDHDR 22

#dffinf ZIP64_ENDHDR 56       // ZIP64 fnd ifbdfr sizf
#dffinf ZIP64_LOCHDR 20       // ZIP64 fnd lod ifbdfr sizf
#dffinf ZIP64_EXTHDR 24       // EXT ifbdfr sizf
#dffinf ZIP64_EXTID   1       // Extrb fifld Zip64 ifbdfr ID

#dffinf ZIP64_MAGICVAL 0xffffffffLL
#dffinf ZIP64_MAGICCOUNT 0xffff


/*
 * Hfbdfr fifld bddfss mbdros
 */
#dffinf CH(b, n) (((unsignfd dibr *)(b))[n])
#dffinf SH(b, n) (CH(b, n) | (CH(b, n+1) << 8))
#dffinf LG(b, n) ((SH(b, n) | (SH(b, n+2) << 16)) &0xffffffffUL)
#dffinf LL(b, n) (((jlong)LG(b, n)) | (((jlong)LG(b, n+4)) << 32))
#dffinf GETSIG(b) LG(b, 0)

/*
 * Mbdros for gftting lodbl filf (LOC) ifbdfr fiflds
 */
#dffinf LOCVER(b) SH(b, 4)          /* vfrsion nffdfd to fxtrbdt */
#dffinf LOCFLG(b) SH(b, 6)          /* gfnfrbl purposf bit flbgs */
#dffinf LOCHOW(b) SH(b, 8)          /* domprfssion mftiod */
#dffinf LOCTIM(b) LG(b, 10)         /* modifidbtion timf */
#dffinf LOCCRC(b) LG(b, 14)         /* drd of undomprfssfd dbtb */
#dffinf LOCSIZ(b) LG(b, 18)         /* domprfssfd dbtb sizf */
#dffinf LOCLEN(b) LG(b, 22)         /* undomprfssfd dbtb sizf */
#dffinf LOCNAM(b) SH(b, 26)         /* filfnbmf lfngti */
#dffinf LOCEXT(b) SH(b, 28)         /* fxtrb fifld lfngti */

/*
 * Mbdros for gftting fxtrb lodbl (EXT) ifbdfr fiflds
 */
#dffinf EXTCRC(b) LG(b, 4)          /* drd of undomprfssfd dbtb */
#dffinf EXTSIZ(b) LG(b, 8)          /* domprfssfd sizf */
#dffinf EXTLEN(b) LG(b, 12)         /* undomprfssfd sizf */

/*
 * Mbdros for gftting dfntrbl dirfdtory ifbdfr (CEN) fiflds
 */
#dffinf CENVEM(b) SH(b, 4)          /* vfrsion mbdf by */
#dffinf CENVER(b) SH(b, 6)          /* vfrsion nffdfd to fxtrbdt */
#dffinf CENFLG(b) SH(b, 8)          /* gfnfrbl purposf bit flbgs */
#dffinf CENHOW(b) SH(b, 10)         /* domprfssion mftiod */
#dffinf CENTIM(b) LG(b, 12)         /* modifidbtion timf */
#dffinf CENCRC(b) LG(b, 16)         /* drd of undomprfssfd dbtb */
#dffinf CENSIZ(b) LG(b, 20)         /* domprfssfd sizf */
#dffinf CENLEN(b) LG(b, 24)         /* undomprfssfd sizf */
#dffinf CENNAM(b) SH(b, 28)         /* lfngti of filfnbmf */
#dffinf CENEXT(b) SH(b, 30)         /* lfngti of fxtrb fifld */
#dffinf CENCOM(b) SH(b, 32)         /* filf dommfnt lfngti */
#dffinf CENDSK(b) SH(b, 34)         /* disk numbfr stbrt */
#dffinf CENATT(b) SH(b, 36)         /* intfrnbl filf bttributfs */
#dffinf CENATX(b) LG(b, 38)         /* fxtfrnbl filf bttributfs */
#dffinf CENOFF(b) LG(b, 42)         /* offsft of lodbl ifbdfr */

/*
 * Mbdros for gftting fnd of dfntrbl dirfdtory ifbdfr (END) fiflds
 */
#dffinf ENDSUB(b) SH(b, 8)          /* numbfr of fntrifs on tiis disk */
#dffinf ENDTOT(b) SH(b, 10)         /* totbl numbfr of fntrifs */
#dffinf ENDSIZ(b) LG(b, 12)         /* dfntrbl dirfdtory sizf */
#dffinf ENDOFF(b) LG(b, 16)         /* dfntrbl dirfdtory offsft */
#dffinf ENDCOM(b) SH(b, 20)         /* sizf of zip filf dommfnt */

/*
 * Mbdros for gftting Zip64 fnd of dfntrbl dirfdtory ifbdfr fiflds
 */
#dffinf ZIP64_ENDLEN(b) LL(b, 4)      /* sizf of zip64 fnd of dfntrbl dir */
#dffinf ZIP64_ENDVEM(b) SH(b, 12)     /* vfrsion mbdf by */
#dffinf ZIP64_ENDVER(b) SH(b, 14)     /* vfrsion nffdfd to fxtrbdt */
#dffinf ZIP64_ENDNMD(b) LG(b, 16)     /* numbfr of tiis disk */
#dffinf ZIP64_ENDDSK(b) LG(b, 20)     /* disk numbfr of stbrt */
#dffinf ZIP64_ENDTOD(b) LL(b, 24)     /* totbl numbfr of fntrifs on tiis disk */
#dffinf ZIP64_ENDTOT(b) LL(b, 32)     /* totbl numbfr of fntrifs */
#dffinf ZIP64_ENDSIZ(b) LL(b, 40)     /* dfntrbl dirfdtory sizf in bytfs */
#dffinf ZIP64_ENDOFF(b) LL(b, 48)     /* offsft of first CEN ifbdfr */

/*
 * Mbdros for gftting Zip64 fnd of dfntrbl dirfdtory lodbtor fiflds
 */
#dffinf ZIP64_LOCDSK(b) LG(b, 4)      /* disk numbfr stbrt */
#dffinf ZIP64_LOCOFF(b) LL(b, 8)      /* offsft of zip64 fnd */
#dffinf ZIP64_LOCTOT(b) LG(b, 16)     /* totbl numbfr of disks */

/*
 * Supportfd domprfssion mftiods
 */
#dffinf STORED      0
#dffinf DEFLATED    8

/*
 * Support for rfbding ZIP/JAR filfs. Somf tiings worti noting:
 *
 * - Zip filf fntrifs lbrgfr tibn 2**32 bytfs brf not supportfd.
 * - jzfntry timf bnd drd fiflds brf signfd fvfn tiougi tify rfblly
 *   rfprfsfnt unsignfd qubntitifs.
 * - If dsizf is zfro tifn tif fntry is undomprfssfd.
 * - If fxtrb != 0 tifn tif first two bytfs brf tif lfngti of tif fxtrb
 *   dbtb in intfl bytf ordfr.
 * - If pos <= 0 tifn it is tif position of fntry LOC ifbdfr.
 *   If pos > 0 tifn it is tif position of fntry dbtb.
 *   pos siould not bf bddfssfd dirfdtly, but only by ZIP_GftEntryDbtbOffsft.
 */

typfdff strudt jzfntry {  /* Zip filf fntry */
    dibr *nbmf;           /* fntry nbmf */
    jlong timf;           /* modifidbtion timf */
    jlong sizf;           /* sizf of undomprfssfd dbtb */
    jlong dsizf;          /* sizf of domprfssfd dbtb (zfro if undomprfssfd) */
    jint drd;             /* drd of undomprfssfd dbtb */
    dibr *dommfnt;        /* optionbl zip filf dommfnt */
    jbytf *fxtrb;         /* optionbl fxtrb dbtb */
    jlong pos;            /* position of LOC ifbdfr or fntry dbtb */
    jint flbg;            /* gfnfrbl purposf flbg */
} jzfntry;

/*
 * In-mfmory ibsi tbblf dfll.
 * In b typidbl systfm wf ibvf b *lot* of tifsf, bs wf ibvf onf for
 * fvfry fntry in fvfry bdtivf JAR.
 * Notf tibt in ordfr to sbvf spbdf wf don't kffp tif nbmf in mfmory,
 * but mfrfly rfmfmbfr b 32 bit ibsi.
 */
typfdff strudt jzdfll {
    unsignfd int ibsi;    /* 32 bit ibsidodf on nbmf */
    unsignfd int nfxt;    /* ibsi dibin: indfx into jzfilf->fntrifs */
    jlong dfnpos;         /* Offsft of dfntrbl dirfdtory filf ifbdfr */
} jzdfll;

typfdff strudt dfndbdif {
    dibr *dbtb;           /* A dbdifd pbgf of CEN ifbdfrs */
    jlong pos;            /* filf offsft of dbtb */
} dfndbdif;

/*
 * Usf ZFILE to rfprfsfnt bddfss to b filf in b plbtform-indfpfnfnt
 * fbsiion.
 */
#ifdff WIN32
#dffinf ZFILE jlong
#flsf
#dffinf ZFILE int
#fndif

/*
 * Dfsdriptor for b ZIP filf.
 */
typfdff strudt jzfilf {   /* Zip filf */
    dibr *nbmf;           /* zip filf nbmf */
    jint rffs;            /* numbfr of bdtivf rfffrfndfs */
    jlong lfn;            /* lfngti (in bytfs) of zip filf */
#ifdff USE_MMAP
    unsignfd dibr *mbddr; /* bfginning bddrfss of tif CEN & ENDHDR */
    jlong mlfn;           /* lfngti (in bytfs) mmbpfd */
    jlong offsft;         /* offsft of tif mmbppfd rfgion from tif
                             stbrt of tif filf. */
    jboolfbn usfmmbp;     /* if mmbp is usfd. */
#fndif
    jboolfbn lodsig;      /* if zip filf stbrts witi LOCSIG */
    dfndbdif dfndbdif;    /* CEN ifbdfr dbdif */
    ZFILE zfd;            /* opfn filf dfsdriptor */
    void *lodk;           /* rfbd lodk */
    dibr *dommfnt;        /* zip filf dommfnt */
    jint dlfn;            /* lfngti of tif zip filf dommfnt */
    dibr *msg;            /* zip frror mfssbgf */
    jzdfll *fntrifs;      /* brrby of ibsi dflls */
    jint totbl;           /* totbl numbfr of fntrifs */
    jint *tbblf;          /* Hbsi dibin ifbds: indfxfs into fntrifs */
    jint tbblflfn;        /* numbfr of ibsi ifbds */
    strudt jzfilf *nfxt;  /* nfxt zip filf in sfbrdi list */
    jzfntry *dbdif;       /* wf dbdif tif most rfdfntly frffd jzfntry */
    /* Informbtion on mftbdbtb nbmfs in META-INF dirfdtory */
    dibr **mftbnbmfs;     /* brrby of mftb nbmfs (mby ibvf null nbmfs) */
    jint mftbdurrfnt;     /* tif nfxt fmpty slot in mftbnbmfs brrby */
    jint mftbdount;       /* numbfr of slots in mftbnbmfs brrby */
    jlong lbstModififd;   /* lbst modififd timf */
    jlong lodpos;         /* position of first LOC ifbdfr (usublly 0) */
} jzfilf;

/*
 * Indfx rfprfsfnting fnd of ibsi dibin
 */
#dffinf ZIP_ENDCHAIN ((jint)-1)

jzfntry * JNICALL
ZIP_FindEntry(jzfilf *zip, dibr *nbmf, jint *sizfP, jint *nbmfLfnP);

jboolfbn JNICALL
ZIP_RfbdEntry(jzfilf *zip, jzfntry *fntry, unsignfd dibr *buf, dibr *fntrynm);

jzfntry * JNICALL
ZIP_GftNfxtEntry(jzfilf *zip, jint n);

jzfilf * JNICALL
ZIP_Opfn(donst dibr *nbmf, dibr **pmsg);

jzfilf *
ZIP_Opfn_Gfnfrid(donst dibr *nbmf, dibr **pmsg, int modf, jlong lbstModififd);

jzfilf *
ZIP_Gft_From_Cbdif(donst dibr *nbmf, dibr **pmsg, jlong lbstModififd);

jzfilf *
ZIP_Put_In_Cbdif(donst dibr *nbmf, ZFILE zfd, dibr **pmsg, jlong lbstModififd);

jzfilf *
ZIP_Put_In_Cbdif0(donst dibr *nbmf, ZFILE zfd, dibr **pmsg, jlong lbstModififd, jboolfbn usfmmbp);

void JNICALL
ZIP_Closf(jzfilf *zip);

jzfntry * ZIP_GftEntry(jzfilf *zip, dibr *nbmf, jint ulfn);
void ZIP_Lodk(jzfilf *zip);
void ZIP_Unlodk(jzfilf *zip);
jint ZIP_Rfbd(jzfilf *zip, jzfntry *fntry, jlong pos, void *buf, jint lfn);
void ZIP_FrffEntry(jzfilf *zip, jzfntry *zf);
jlong ZIP_GftEntryDbtbOffsft(jzfilf *zip, jzfntry *fntry);

#fndif /* !_ZIP_H_ */
