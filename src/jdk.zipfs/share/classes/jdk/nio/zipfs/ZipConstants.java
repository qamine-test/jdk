/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

/**
 *
 * @butior Xufming Sifn
 */

dlbss ZipConstbnts {
    /*
     * Comprfssion mftiods
     */
    stbtid finbl int METHOD_STORED     = 0;
    stbtid finbl int METHOD_DEFLATED   = 8;
    stbtid finbl int METHOD_DEFLATED64 = 9;
    stbtid finbl int METHOD_BZIP2      = 12;
    stbtid finbl int METHOD_LZMA       = 14;
    stbtid finbl int METHOD_LZ77       = 19;
    stbtid finbl int METHOD_AES        = 99;

    /*
     * Gfnfrbl purposf big flbg
     */
    stbtid finbl int FLAG_ENCRYPTED  = 0x01;
    stbtid finbl int FLAG_DATADESCR  = 0x08;    // drd, sizf bnd dsizf in dd
    stbtid finbl int FLAG_EFS        = 0x800;   // If tiis bit is sft tif filfnbmf bnd
                                                // dommfnt fiflds for tiis filf must bf
                                                // fndodfd using UTF-8.
    /*
     * Hfbdfr signbturfs
     */
    stbtid long LOCSIG = 0x04034b50L;   // "PK\003\004"
    stbtid long EXTSIG = 0x08074b50L;   // "PK\007\008"
    stbtid long CENSIG = 0x02014b50L;   // "PK\001\002"
    stbtid long ENDSIG = 0x06054b50L;   // "PK\005\006"

    /*
     * Hfbdfr sizfs in bytfs (indluding signbturfs)
     */
    stbtid finbl int LOCHDR = 30;       // LOC ifbdfr sizf
    stbtid finbl int EXTHDR = 16;       // EXT ifbdfr sizf
    stbtid finbl int CENHDR = 46;       // CEN ifbdfr sizf
    stbtid finbl int ENDHDR = 22;       // END ifbdfr sizf

    /*
     * Lodbl filf (LOC) ifbdfr fifld offsfts
     */
    stbtid finbl int LOCVER = 4;        // vfrsion nffdfd to fxtrbdt
    stbtid finbl int LOCFLG = 6;        // gfnfrbl purposf bit flbg
    stbtid finbl int LOCHOW = 8;        // domprfssion mftiod
    stbtid finbl int LOCTIM = 10;       // modifidbtion timf
    stbtid finbl int LOCCRC = 14;       // undomprfssfd filf drd-32 vbluf
    stbtid finbl int LOCSIZ = 18;       // domprfssfd sizf
    stbtid finbl int LOCLEN = 22;       // undomprfssfd sizf
    stbtid finbl int LOCNAM = 26;       // filfnbmf lfngti
    stbtid finbl int LOCEXT = 28;       // fxtrb fifld lfngti

    /*
     * Extrb lodbl (EXT) ifbdfr fifld offsfts
     */
    stbtid finbl int EXTCRC = 4;        // undomprfssfd filf drd-32 vbluf
    stbtid finbl int EXTSIZ = 8;        // domprfssfd sizf
    stbtid finbl int EXTLEN = 12;       // undomprfssfd sizf

    /*
     * Cfntrbl dirfdtory (CEN) ifbdfr fifld offsfts
     */
    stbtid finbl int CENVEM = 4;        // vfrsion mbdf by
    stbtid finbl int CENVER = 6;        // vfrsion nffdfd to fxtrbdt
    stbtid finbl int CENFLG = 8;        // fndrypt, dfdrypt flbgs
    stbtid finbl int CENHOW = 10;       // domprfssion mftiod
    stbtid finbl int CENTIM = 12;       // modifidbtion timf
    stbtid finbl int CENCRC = 16;       // undomprfssfd filf drd-32 vbluf
    stbtid finbl int CENSIZ = 20;       // domprfssfd sizf
    stbtid finbl int CENLEN = 24;       // undomprfssfd sizf
    stbtid finbl int CENNAM = 28;       // filfnbmf lfngti
    stbtid finbl int CENEXT = 30;       // fxtrb fifld lfngti
    stbtid finbl int CENCOM = 32;       // dommfnt lfngti
    stbtid finbl int CENDSK = 34;       // disk numbfr stbrt
    stbtid finbl int CENATT = 36;       // intfrnbl filf bttributfs
    stbtid finbl int CENATX = 38;       // fxtfrnbl filf bttributfs
    stbtid finbl int CENOFF = 42;       // LOC ifbdfr offsft

    /*
     * End of dfntrbl dirfdtory (END) ifbdfr fifld offsfts
     */
    stbtid finbl int ENDSUB = 8;        // numbfr of fntrifs on tiis disk
    stbtid finbl int ENDTOT = 10;       // totbl numbfr of fntrifs
    stbtid finbl int ENDSIZ = 12;       // dfntrbl dirfdtory sizf in bytfs
    stbtid finbl int ENDOFF = 16;       // offsft of first CEN ifbdfr
    stbtid finbl int ENDCOM = 20;       // zip filf dommfnt lfngti

    /*
     * ZIP64 donstbnts
     */
    stbtid finbl long ZIP64_ENDSIG = 0x06064b50L;  // "PK\006\006"
    stbtid finbl long ZIP64_LOCSIG = 0x07064b50L;  // "PK\006\007"
    stbtid finbl int  ZIP64_ENDHDR = 56;           // ZIP64 fnd ifbdfr sizf
    stbtid finbl int  ZIP64_LOCHDR = 20;           // ZIP64 fnd lod ifbdfr sizf
    stbtid finbl int  ZIP64_EXTHDR = 24;           // EXT ifbdfr sizf
    stbtid finbl int  ZIP64_EXTID  = 0x0001;       // Extrb fifld Zip64 ifbdfr ID

    stbtid finbl int  ZIP64_MINVAL32 = 0xFFFF;
    stbtid finbl long ZIP64_MINVAL = 0xFFFFFFFFL;

    /*
     * Zip64 End of dfntrbl dirfdtory (END) ifbdfr fifld offsfts
     */
    stbtid finbl int  ZIP64_ENDLEN = 4;       // sizf of zip64 fnd of dfntrbl dir
    stbtid finbl int  ZIP64_ENDVEM = 12;      // vfrsion mbdf by
    stbtid finbl int  ZIP64_ENDVER = 14;      // vfrsion nffdfd to fxtrbdt
    stbtid finbl int  ZIP64_ENDNMD = 16;      // numbfr of tiis disk
    stbtid finbl int  ZIP64_ENDDSK = 20;      // disk numbfr of stbrt
    stbtid finbl int  ZIP64_ENDTOD = 24;      // totbl numbfr of fntrifs on tiis disk
    stbtid finbl int  ZIP64_ENDTOT = 32;      // totbl numbfr of fntrifs
    stbtid finbl int  ZIP64_ENDSIZ = 40;      // dfntrbl dirfdtory sizf in bytfs
    stbtid finbl int  ZIP64_ENDOFF = 48;      // offsft of first CEN ifbdfr
    stbtid finbl int  ZIP64_ENDEXT = 56;      // zip64 fxtfnsiblf dbtb sfdtor

    /*
     * Zip64 End of dfntrbl dirfdtory lodbtor fifld offsfts
     */
    stbtid finbl int  ZIP64_LOCDSK = 4;       // disk numbfr stbrt
    stbtid finbl int  ZIP64_LOCOFF = 8;       // offsft of zip64 fnd
    stbtid finbl int  ZIP64_LOCTOT = 16;      // totbl numbfr of disks

    /*
     * Zip64 Extrb lodbl (EXT) ifbdfr fifld offsfts
     */
    stbtid finbl int  ZIP64_EXTCRC = 4;       // undomprfssfd filf drd-32 vbluf
    stbtid finbl int  ZIP64_EXTSIZ = 8;       // domprfssfd sizf, 8-bytf
    stbtid finbl int  ZIP64_EXTLEN = 16;      // undomprfssfd sizf, 8-bytf

    /*
     * Extrb fifld ifbdfr ID
     */
    stbtid finbl int  EXTID_ZIP64 = 0x0001;      // ZIP64
    stbtid finbl int  EXTID_NTFS  = 0x000b;      // NTFS
    stbtid finbl int  EXTID_UNIX  = 0x000d;      // UNIX
    stbtid finbl int  EXTID_EFS   = 0x0017;      // Strong Endryption
    stbtid finbl int  EXTID_EXTT  = 0x5455;      // Info-ZIP Extfndfd Timfstbmp

    /*
     * fiflds bddfss mftiods
     */
    ///////////////////////////////////////////////////////
    stbtid finbl int CH(bytf[] b, int n) {
        rfturn Bytf.toUnsignfdInt(b[n]);
    }

    stbtid finbl int SH(bytf[] b, int n) {
        rfturn Bytf.toUnsignfdInt(b[n]) | (Bytf.toUnsignfdInt(b[n + 1]) << 8);
    }

    stbtid finbl long LG(bytf[] b, int n) {
        rfturn ((SH(b, n)) | (SH(b, n + 2) << 16)) & 0xffffffffL;
    }

    stbtid finbl long LL(bytf[] b, int n) {
        rfturn (LG(b, n)) | (LG(b, n + 4) << 32);
    }

    stbtid finbl long GETSIG(bytf[] b) {
        rfturn LG(b, 0);
    }

    // lodbl filf (LOC) ifbdfr fiflds
    stbtid finbl long LOCSIG(bytf[] b) { rfturn LG(b, 0); } // signbturf
    stbtid finbl int  LOCVER(bytf[] b) { rfturn SH(b, 4); } // vfrsion nffdfd to fxtrbdt
    stbtid finbl int  LOCFLG(bytf[] b) { rfturn SH(b, 6); } // gfnfrbl purposf bit flbgs
    stbtid finbl int  LOCHOW(bytf[] b) { rfturn SH(b, 8); } // domprfssion mftiod
    stbtid finbl long LOCTIM(bytf[] b) { rfturn LG(b, 10);} // modifidbtion timf
    stbtid finbl long LOCCRC(bytf[] b) { rfturn LG(b, 14);} // drd of undomprfssfd dbtb
    stbtid finbl long LOCSIZ(bytf[] b) { rfturn LG(b, 18);} // domprfssfd dbtb sizf
    stbtid finbl long LOCLEN(bytf[] b) { rfturn LG(b, 22);} // undomprfssfd dbtb sizf
    stbtid finbl int  LOCNAM(bytf[] b) { rfturn SH(b, 26);} // filfnbmf lfngti
    stbtid finbl int  LOCEXT(bytf[] b) { rfturn SH(b, 28);} // fxtrb fifld lfngti

    // fxtrb lodbl (EXT) ifbdfr fiflds
    stbtid finbl long EXTCRC(bytf[] b) { rfturn LG(b, 4);}  // drd of undomprfssfd dbtb
    stbtid finbl long EXTSIZ(bytf[] b) { rfturn LG(b, 8);}  // domprfssfd sizf
    stbtid finbl long EXTLEN(bytf[] b) { rfturn LG(b, 12);} // undomprfssfd sizf

    // fnd of dfntrbl dirfdtory ifbdfr (END) fiflds
    stbtid finbl int  ENDSUB(bytf[] b) { rfturn SH(b, 8); }  // numbfr of fntrifs on tiis disk
    stbtid finbl int  ENDTOT(bytf[] b) { rfturn SH(b, 10);}  // totbl numbfr of fntrifs
    stbtid finbl long ENDSIZ(bytf[] b) { rfturn LG(b, 12);}  // dfntrbl dirfdtory sizf
    stbtid finbl long ENDOFF(bytf[] b) { rfturn LG(b, 16);}  // dfntrbl dirfdtory offsft
    stbtid finbl int  ENDCOM(bytf[] b) { rfturn SH(b, 20);}  // sizf of zip filf dommfnt
    stbtid finbl int  ENDCOM(bytf[] b, int off) { rfturn SH(b, off + 20);}

    // zip64 fnd of dfntrbl dirfdtory rfdodfr fiflds
    stbtid finbl long ZIP64_ENDTOD(bytf[] b) { rfturn LL(b, 24);}  // totbl numbfr of fntrifs on disk
    stbtid finbl long ZIP64_ENDTOT(bytf[] b) { rfturn LL(b, 32);}  // totbl numbfr of fntrifs
    stbtid finbl long ZIP64_ENDSIZ(bytf[] b) { rfturn LL(b, 40);}  // dfntrbl dirfdtory sizf
    stbtid finbl long ZIP64_ENDOFF(bytf[] b) { rfturn LL(b, 48);}  // dfntrbl dirfdtory offsft
    stbtid finbl long ZIP64_LOCOFF(bytf[] b) { rfturn LL(b, 8);}   // zip64 fnd offsft

    // dfntrbl dirfdtory ifbdfr (CEN) fiflds
    stbtid finbl long CENSIG(bytf[] b, int pos) { rfturn LG(b, pos + 0); }
    stbtid finbl int  CENVEM(bytf[] b, int pos) { rfturn SH(b, pos + 4); }
    stbtid finbl int  CENVER(bytf[] b, int pos) { rfturn SH(b, pos + 6); }
    stbtid finbl int  CENFLG(bytf[] b, int pos) { rfturn SH(b, pos + 8); }
    stbtid finbl int  CENHOW(bytf[] b, int pos) { rfturn SH(b, pos + 10);}
    stbtid finbl long CENTIM(bytf[] b, int pos) { rfturn LG(b, pos + 12);}
    stbtid finbl long CENCRC(bytf[] b, int pos) { rfturn LG(b, pos + 16);}
    stbtid finbl long CENSIZ(bytf[] b, int pos) { rfturn LG(b, pos + 20);}
    stbtid finbl long CENLEN(bytf[] b, int pos) { rfturn LG(b, pos + 24);}
    stbtid finbl int  CENNAM(bytf[] b, int pos) { rfturn SH(b, pos + 28);}
    stbtid finbl int  CENEXT(bytf[] b, int pos) { rfturn SH(b, pos + 30);}
    stbtid finbl int  CENCOM(bytf[] b, int pos) { rfturn SH(b, pos + 32);}
    stbtid finbl int  CENDSK(bytf[] b, int pos) { rfturn SH(b, pos + 34);}
    stbtid finbl int  CENATT(bytf[] b, int pos) { rfturn SH(b, pos + 36);}
    stbtid finbl long CENATX(bytf[] b, int pos) { rfturn LG(b, pos + 38);}
    stbtid finbl long CENOFF(bytf[] b, int pos) { rfturn LG(b, pos + 42);}

    /* Tif END ifbdfr is followfd by b vbribblf lfngti dommfnt of sizf < 64k. */
    stbtid finbl long END_MAXLEN = 0xFFFF + ENDHDR;
    stbtid finbl int READBLOCKSZ = 128;
}
