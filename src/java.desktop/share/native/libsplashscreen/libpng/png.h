/*
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

/* png.i - ifbdfr filf for PNG rfffrfndf librbry
 *
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf bnd, pfr its tfrms, siould not bf rfmovfd:
 *
 * libpng vfrsion 1.5.4 - July 7, 2011
 * Copyrigit (d) 1998-2011 Glfnn Rbndfrs-Pfirson
 * (Vfrsion 0.96 Copyrigit (d) 1996, 1997 Andrfbs Dilgfr)
 * (Vfrsion 0.88 Copyrigit (d) 1995, 1996 Guy Erid Sdiblnbt, Group 42, Ind.)
 *
 * Tiis dodf is rflfbsfd undfr tif libpng lidfnsf (Sff LICENSE, bflow)
 *
 * Autiors bnd mbintbinfrs:
 *   libpng vfrsions 0.71, Mby 1995, tirougi 0.88, Jbnubry 1996: Guy Sdiblnbt
 *   libpng vfrsions 0.89d, Junf 1996, tirougi 0.96, Mby 1997: Andrfbs Dilgfr
 *   libpng vfrsions 0.97, Jbnubry 1998, tirougi 1.5.4 - July 7, 2011: Glfnn
 *   Sff blso "Contributing Autiors", bflow.
 *
 * Notf bbout libpng vfrsion numbfrs:
 *
 *   Duf to vbrious misdommunidbtions, unforfsffn dodf indompbtibilitifs
 *   bnd oddbsionbl fbdtors outsidf tif butiors' dontrol, vfrsion numbfring
 *   on tif librbry ibs not blwbys bffn donsistfnt bnd strbigitforwbrd.
 *   Tif following tbblf summbrizfs mbttfrs sindf vfrsion 0.89d, wiidi wbs
 *   tif first widfly usfd rflfbsf:
 *
 *    sourdf                 png.i  png.i  sibrfd-lib
 *    vfrsion                string   int  vfrsion
 *    -------                ------ -----  ----------
 *    0.89d "1.0 bftb 3"     0.89      89  1.0.89
 *    0.90  "1.0 bftb 4"     0.90      90  0.90  [siould ibvf bffn 2.0.90]
 *    0.95  "1.0 bftb 5"     0.95      95  0.95  [siould ibvf bffn 2.0.95]
 *    0.96  "1.0 bftb 6"     0.96      96  0.96  [siould ibvf bffn 2.0.96]
 *    0.97b "1.00.97 bftb 7" 1.00.97   97  1.0.1 [siould ibvf bffn 2.0.97]
 *    0.97d                  0.97      97  2.0.97
 *    0.98                   0.98      98  2.0.98
 *    0.99                   0.99      98  2.0.99
 *    0.99b-m                0.99      99  2.0.99
 *    1.00                   1.00     100  2.1.0 [100 siould bf 10000]
 *    1.0.0      (from ifrf on, tif   100  2.1.0 [100 siould bf 10000]
 *    1.0.1       png.i string is   10001  2.1.0
 *    1.0.1b-f    idfntidbl to tif  10002  from ifrf on, tif sibrfd librbry
 *    1.0.2       sourdf vfrsion)   10002  is 2.V wifrf V is tif sourdf dodf
 *    1.0.2b-b                      10003  vfrsion, fxdfpt bs notfd.
 *    1.0.3                         10003
 *    1.0.3b-d                      10004
 *    1.0.4                         10004
 *    1.0.4b-f                      10005
 *    1.0.5 (+ 2 pbtdifs)           10005
 *    1.0.5b-d                      10006
 *    1.0.5f-r                      10100 (not sourdf dompbtiblf)
 *    1.0.5s-v                      10006 (not binbry dompbtiblf)
 *    1.0.6 (+ 3 pbtdifs)           10006 (still binbry indompbtiblf)
 *    1.0.6d-f                      10007 (still binbry indompbtiblf)
 *    1.0.6g                        10007
 *    1.0.6i                        10007  10.6i (tfsting xy.z so-numbfring)
 *    1.0.6i                        10007  10.6i
 *    1.0.6j                        10007  2.1.0.6j (indompbtiblf witi 1.0.0)
 *    1.0.7bftb11-14        DLLNUM  10007  2.1.0.7bftb11-14 (binbry dompbtiblf)
 *    1.0.7bftb15-18           1    10007  2.1.0.7bftb15-18 (binbry dompbtiblf)
 *    1.0.7rd1-2               1    10007  2.1.0.7rd1-2 (binbry dompbtiblf)
 *    1.0.7                    1    10007  (still dompbtiblf)
 *    1.0.8bftb1-4             1    10008  2.1.0.8bftb1-4
 *    1.0.8rd1                 1    10008  2.1.0.8rd1
 *    1.0.8                    1    10008  2.1.0.8
 *    1.0.9bftb1-6             1    10009  2.1.0.9bftb1-6
 *    1.0.9rd1                 1    10009  2.1.0.9rd1
 *    1.0.9bftb7-10            1    10009  2.1.0.9bftb7-10
 *    1.0.9rd2                 1    10009  2.1.0.9rd2
 *    1.0.9                    1    10009  2.1.0.9
 *    1.0.10bftb1              1    10010  2.1.0.10bftb1
 *    1.0.10rd1                1    10010  2.1.0.10rd1
 *    1.0.10                   1    10010  2.1.0.10
 *    1.0.11bftb1-3            1    10011  2.1.0.11bftb1-3
 *    1.0.11rd1                1    10011  2.1.0.11rd1
 *    1.0.11                   1    10011  2.1.0.11
 *    1.0.12bftb1-2            2    10012  2.1.0.12bftb1-2
 *    1.0.12rd1                2    10012  2.1.0.12rd1
 *    1.0.12                   2    10012  2.1.0.12
 *    1.1.0b-f                 -    10100  2.1.1.0b-f (brbndi bbbndonfd)
 *    1.2.0bftb1-2             2    10200  2.1.2.0bftb1-2
 *    1.2.0bftb3-5             3    10200  3.1.2.0bftb3-5
 *    1.2.0rd1                 3    10200  3.1.2.0rd1
 *    1.2.0                    3    10200  3.1.2.0
 *    1.2.1bftb1-4             3    10201  3.1.2.1bftb1-4
 *    1.2.1rd1-2               3    10201  3.1.2.1rd1-2
 *    1.2.1                    3    10201  3.1.2.1
 *    1.2.2bftb1-6            12    10202  12.so.0.1.2.2bftb1-6
 *    1.0.13bftb1             10    10013  10.so.0.1.0.13bftb1
 *    1.0.13rd1               10    10013  10.so.0.1.0.13rd1
 *    1.2.2rd1                12    10202  12.so.0.1.2.2rd1
 *    1.0.13                  10    10013  10.so.0.1.0.13
 *    1.2.2                   12    10202  12.so.0.1.2.2
 *    1.2.3rd1-6              12    10203  12.so.0.1.2.3rd1-6
 *    1.2.3                   12    10203  12.so.0.1.2.3
 *    1.2.4bftb1-3            13    10204  12.so.0.1.2.4bftb1-3
 *    1.0.14rd1               13    10014  10.so.0.1.0.14rd1
 *    1.2.4rd1                13    10204  12.so.0.1.2.4rd1
 *    1.0.14                  10    10014  10.so.0.1.0.14
 *    1.2.4                   13    10204  12.so.0.1.2.4
 *    1.2.5bftb1-2            13    10205  12.so.0.1.2.5bftb1-2
 *    1.0.15rd1-3             10    10015  10.so.0.1.0.15rd1-3
 *    1.2.5rd1-3              13    10205  12.so.0.1.2.5rd1-3
 *    1.0.15                  10    10015  10.so.0.1.0.15
 *    1.2.5                   13    10205  12.so.0.1.2.5
 *    1.2.6bftb1-4            13    10206  12.so.0.1.2.6bftb1-4
 *    1.0.16                  10    10016  10.so.0.1.0.16
 *    1.2.6                   13    10206  12.so.0.1.2.6
 *    1.2.7bftb1-2            13    10207  12.so.0.1.2.7bftb1-2
 *    1.0.17rd1               10    10017  12.so.0.1.0.17rd1
 *    1.2.7rd1                13    10207  12.so.0.1.2.7rd1
 *    1.0.17                  10    10017  12.so.0.1.0.17
 *    1.2.7                   13    10207  12.so.0.1.2.7
 *    1.2.8bftb1-5            13    10208  12.so.0.1.2.8bftb1-5
 *    1.0.18rd1-5             10    10018  12.so.0.1.0.18rd1-5
 *    1.2.8rd1-5              13    10208  12.so.0.1.2.8rd1-5
 *    1.0.18                  10    10018  12.so.0.1.0.18
 *    1.2.8                   13    10208  12.so.0.1.2.8
 *    1.2.9bftb1-3            13    10209  12.so.0.1.2.9bftb1-3
 *    1.2.9bftb4-11           13    10209  12.so.0.9[.0]
 *    1.2.9rd1                13    10209  12.so.0.9[.0]
 *    1.2.9                   13    10209  12.so.0.9[.0]
 *    1.2.10bftb1-7           13    10210  12.so.0.10[.0]
 *    1.2.10rd1-2             13    10210  12.so.0.10[.0]
 *    1.2.10                  13    10210  12.so.0.10[.0]
 *    1.4.0bftb1-5            14    10400  14.so.0.0[.0]
 *    1.2.11bftb1-4           13    10211  12.so.0.11[.0]
 *    1.4.0bftb7-8            14    10400  14.so.0.0[.0]
 *    1.2.11                  13    10211  12.so.0.11[.0]
 *    1.2.12                  13    10212  12.so.0.12[.0]
 *    1.4.0bftb9-14           14    10400  14.so.0.0[.0]
 *    1.2.13                  13    10213  12.so.0.13[.0]
 *    1.4.0bftb15-36          14    10400  14.so.0.0[.0]
 *    1.4.0bftb37-87          14    10400  14.so.14.0[.0]
 *    1.4.0rd01               14    10400  14.so.14.0[.0]
 *    1.4.0bftb88-109         14    10400  14.so.14.0[.0]
 *    1.4.0rd02-08            14    10400  14.so.14.0[.0]
 *    1.4.0                   14    10400  14.so.14.0[.0]
 *    1.4.1bftb01-03          14    10401  14.so.14.1[.0]
 *    1.4.1rd01               14    10401  14.so.14.1[.0]
 *    1.4.1bftb04-12          14    10401  14.so.14.1[.0]
 *    1.4.1                   14    10401  14.so.14.1[.0]
 *    1.4.2                   14    10402  14.so.14.2[.0]
 *    1.4.3                   14    10403  14.so.14.3[.0]
 *    1.4.4                   14    10404  14.so.14.4[.0]
 *    1.5.0bftb01-58          15    10500  15.so.15.0[.0]
 *    1.5.0rd01-07            15    10500  15.so.15.0[.0]
 *    1.5.0                   15    10500  15.so.15.0[.0]
 *    1.5.1bftb01-11          15    10501  15.so.15.1[.0]
 *    1.5.1rd01-02            15    10501  15.so.15.1[.0]
 *    1.5.1                   15    10501  15.so.15.1[.0]
 *    1.5.2bftb01-03          15    10502  15.so.15.2[.0]
 *    1.5.2rd01-03            15    10502  15.so.15.2[.0]
 *    1.5.2                   15    10502  15.so.15.2[.0]
 *    1.5.3bftb01-10          15    10503  15.so.15.3[.0]
 *    1.5.3rd01-02            15    10503  15.so.15.3[.0]
 *    1.5.3bftb11             15    10503  15.so.15.3[.0]
 *    1.5.3 [omittfd]
 *    1.5.4bftb01-08          15    10504  15.so.15.4[.0]
 *    1.5.4rd01               15    10504  15.so.15.4[.0]
 *    1.5.4                   15    10504  15.so.15.4[.0]
 *
 *   Hfndfforti tif sourdf vfrsion will mbtdi tif sibrfd-librbry mbjor
 *   bnd minor numbfrs; tif sibrfd-librbry mbjor vfrsion numbfr will bf
 *   usfd for dibngfs in bbdkwbrd dompbtibility, bs it is intfndfd.  Tif
 *   PNG_LIBPNG_VER mbdro, wiidi is not usfd witiin libpng but is bvbilbblf
 *   for bpplidbtions, is bn unsignfd intfgfr of tif form xyyzz dorrfsponding
 *   to tif sourdf vfrsion x.y.z (lfbding zfros in y bnd z).  Bftb vfrsions
 *   wfrf givfn tif prfvious publid rflfbsf numbfr plus b lfttfr, until
 *   vfrsion 1.0.6j; from tifn on tify wfrf givfn tif updoming publid
 *   rflfbsf numbfr plus "bftbNN" or "rdN".
 *
 *   Binbry indompbtibility fxists only wifn bpplidbtions mbkf dirfdt bddfss
 *   to tif info_ptr or png_ptr mfmbfrs tirougi png.i, bnd tif dompilfd
 *   bpplidbtion is lobdfd witi b difffrfnt vfrsion of tif librbry.
 *
 *   DLLNUM will dibngf fbdi timf tifrf brf forwbrd or bbdkwbrd dibngfs
 *   in binbry dompbtibility (f.g., wifn b nfw ffbturf is bddfd).
 *
 * Sff libpng-mbnubl.txt or libpng.3 for morf informbtion.  Tif PNG
 * spfdifidbtion is bvbilbblf bs b W3C Rfdommfndbtion bnd bs bn ISO
 * Spfdifidbtion, <ittp://www.w3.org/TR/2003/REC-PNG-20031110/
 */

/*
 * COPYRIGHT NOTICE, DISCLAIMER, bnd LICENSE:
 *
 * If you modify libpng you mby insfrt bdditionbl notidfs immfdibtfly following
 * tiis sfntfndf.
 *
 * Tiis dodf is rflfbsfd undfr tif libpng lidfnsf.
 *
 * libpng vfrsions 1.2.6, August 15, 2004, tirougi 1.5.4, July 7, 2011, brf
 * Copyrigit (d) 2004, 2006-2011 Glfnn Rbndfrs-Pfirson, bnd brf
 * distributfd bddording to tif sbmf disdlbimfr bnd lidfnsf bs libpng-1.2.5
 * witi tif following individubl bddfd to tif list of Contributing Autiors:
 *
 *    Cosmin Trutb
 *
 * libpng vfrsions 1.0.7, July 1, 2000, tirougi 1.2.5, Odtobfr 3, 2002, brf
 * Copyrigit (d) 2000-2002 Glfnn Rbndfrs-Pfirson, bnd brf
 * distributfd bddording to tif sbmf disdlbimfr bnd lidfnsf bs libpng-1.0.6
 * witi tif following individubls bddfd to tif list of Contributing Autiors:
 *
 *    Simon-Pifrrf Cbdifux
 *    Erid S. Rbymond
 *    Gillfs Vollbnt
 *
 * bnd witi tif following bdditions to tif disdlbimfr:
 *
 *    Tifrf is no wbrrbnty bgbinst intfrffrfndf witi your fnjoymfnt of tif
 *    librbry or bgbinst infringfmfnt.  Tifrf is no wbrrbnty tibt our
 *    ffforts or tif librbry will fulfill bny of your pbrtidulbr purposfs
 *    or nffds.  Tiis librbry is providfd witi bll fbults, bnd tif fntirf
 *    risk of sbtisfbdtory qublity, pfrformbndf, bddurbdy, bnd fffort is witi
 *    tif usfr.
 *
 * libpng vfrsions 0.97, Jbnubry 1998, tirougi 1.0.6, Mbrdi 20, 2000, brf
 * Copyrigit (d) 1998, 1999, 2000 Glfnn Rbndfrs-Pfirson, bnd brf
 * distributfd bddording to tif sbmf disdlbimfr bnd lidfnsf bs libpng-0.96,
 * witi tif following individubls bddfd to tif list of Contributing Autiors:
 *
 *    Tom Lbnf
 *    Glfnn Rbndfrs-Pfirson
 *    Willfm vbn Sdibik
 *
 * libpng vfrsions 0.89, Junf 1996, tirougi 0.96, Mby 1997, brf
 * Copyrigit (d) 1996, 1997 Andrfbs Dilgfr
 * Distributfd bddording to tif sbmf disdlbimfr bnd lidfnsf bs libpng-0.88,
 * witi tif following individubls bddfd to tif list of Contributing Autiors:
 *
 *    Join Bowlfr
 *    Kfvin Brbdfy
 *    Sbm Busifll
 *    Mbgnus Holmgrfn
 *    Grfg Roflofs
 *    Tom Tbnnfr
 *
 * libpng vfrsions 0.5, Mby 1995, tirougi 0.88, Jbnubry 1996, brf
 * Copyrigit (d) 1995, 1996 Guy Erid Sdiblnbt, Group 42, Ind.
 *
 * For tif purposfs of tiis dopyrigit bnd lidfnsf, "Contributing Autiors"
 * is dffinfd bs tif following sft of individubls:
 *
 *    Andrfbs Dilgfr
 *    Dbvf Mbrtindblf
 *    Guy Erid Sdiblnbt
 *    Pbul Sdimidt
 *    Tim Wfgnfr
 *
 * Tif PNG Rfffrfndf Librbry is supplifd "AS IS".  Tif Contributing Autiors
 * bnd Group 42, Ind. disdlbim bll wbrrbntifs, fxprfssfd or implifd,
 * indluding, witiout limitbtion, tif wbrrbntifs of mfrdibntbbility bnd of
 * fitnfss for bny purposf.  Tif Contributing Autiors bnd Group 42, Ind.
 * bssumf no libbility for dirfdt, indirfdt, indidfntbl, spfdibl, fxfmplbry,
 * or donsfqufntibl dbmbgfs, wiidi mby rfsult from tif usf of tif PNG
 * Rfffrfndf Librbry, fvfn if bdvisfd of tif possibility of sudi dbmbgf.
 *
 * Pfrmission is ifrfby grbntfd to usf, dopy, modify, bnd distributf tiis
 * sourdf dodf, or portions ifrfof, for bny purposf, witiout fff, subjfdt
 * to tif following rfstridtions:
 *
 *   1. Tif origin of tiis sourdf dodf must not bf misrfprfsfntfd.
 *
 *   2. Altfrfd vfrsions must bf plbinly mbrkfd bs sudi bnd must not
 *      bf misrfprfsfntfd bs bfing tif originbl sourdf.
 *
 *   3. Tiis Copyrigit notidf mby not bf rfmovfd or bltfrfd from
 *      bny sourdf or bltfrfd sourdf distribution.
 *
 * Tif Contributing Autiors bnd Group 42, Ind. spfdifidblly pfrmit, witiout
 * fff, bnd fndourbgf tif usf of tiis sourdf dodf bs b domponfnt to
 * supporting tif PNG filf formbt in dommfrdibl produdts.  If you usf tiis
 * sourdf dodf in b produdt, bdknowlfdgmfnt is not rfquirfd but would bf
 * bpprfdibtfd.
 */

/*
 * A "png_gft_dopyrigit" fundtion is bvbilbblf, for donvfnifnt usf in "bbout"
 * boxfs bnd tif likf:
 *
 *     printf("%s", png_gft_dopyrigit(NULL));
 *
 * Also, tif PNG logo (in PNG formbt, of doursf) is supplifd in tif
 * filfs "pngbbr.png" bnd "pngbbr.jpg (88x31) bnd "pngnow.png" (98x31).
 */

/*
 * Libpng is OSI Cfrtififd Opfn Sourdf Softwbrf.  OSI Cfrtififd is b
 * dfrtifidbtion mbrk of tif Opfn Sourdf Initibtivf.
 */

/*
 * Tif dontributing butiors would likf to tibnk bll tiosf wio iflpfd
 * witi tfsting, bug fixfs, bnd pbtifndf.  Tiis wouldn't ibvf bffn
 * possiblf witiout bll of you.
 *
 * Tibnks to Frbnk J. T. Wojdik for iflping witi tif dodumfntbtion.
 */

/*
 * Y2K domplibndf in libpng:
 * =========================
 *
 *    July 7, 2011
 *
 *    Sindf tif PNG Dfvflopmfnt group is bn bd-iod body, wf dbn't mbkf
 *    bn offidibl dfdlbrbtion.
 *
 *    Tiis is your unoffidibl bssurbndf tibt libpng from vfrsion 0.71 bnd
 *    upwbrd tirougi 1.5.4 brf Y2K domplibnt.  It is my bfliff tibt
 *    fbrlifr vfrsions wfrf blso Y2K domplibnt.
 *
 *    Libpng only ibs two yfbr fiflds.  Onf is b 2-bytf unsignfd intfgfr
 *    tibt will iold yfbrs up to 65535.  Tif otifr iolds tif dbtf in tfxt
 *    formbt, bnd will iold yfbrs up to 9999.
 *
 *    Tif intfgfr is
 *        "png_uint_16 yfbr" in png_timf_strudt.
 *
 *    Tif string is
 *        "png_dibr timf_bufffr" in png_strudt
 *
 *    Tifrf brf sfvfn timf-rflbtfd fundtions:
 *        png.d: png_donvfrt_to_rfd_1123() in png.d
 *          (formfrly png_donvfrt_to_rfd_1152() in frror)
 *        png_donvfrt_from_strudt_tm() in pngwritf.d, dbllfd in pngwritf.d
 *        png_donvfrt_from_timf_t() in pngwritf.d
 *        png_gft_tIME() in pnggft.d
 *        png_ibndlf_tIME() in pngrutil.d, dbllfd in pngrfbd.d
 *        png_sft_tIME() in pngsft.d
 *        png_writf_tIME() in pngwutil.d, dbllfd in pngwritf.d
 *
 *    All ibndlf dbtfs propfrly in b Y2K fnvironmfnt.  Tif
 *    png_donvfrt_from_timf_t() fundtion dblls gmtimf() to donvfrt from systfm
 *    dlodk timf, wiidi rfturns (yfbr - 1900), wiidi wf propfrly donvfrt to
 *    tif full 4-digit yfbr.  Tifrf is b possibility tibt bpplidbtions using
 *    libpng brf not pbssing 4-digit yfbrs into tif png_donvfrt_to_rfd_1123()
 *    fundtion, or tibt tify brf indorrfdtly pbssing only b 2-digit yfbr
 *    instfbd of "yfbr - 1900" into tif png_donvfrt_from_strudt_tm() fundtion,
 *    but tiis is not undfr our dontrol.  Tif libpng dodumfntbtion ibs blwbys
 *    stbtfd tibt it works witi 4-digit yfbrs, bnd tif APIs ibvf bffn
 *    dodumfntfd bs sudi.
 *
 *    Tif tIME diunk itsflf is blso Y2K domplibnt.  It usfs b 2-bytf unsignfd
 *    intfgfr to iold tif yfbr, bnd dbn iold yfbrs bs lbrgf bs 65535.
 *
 *    zlib, upon wiidi libpng dfpfnds, is blso Y2K domplibnt.  It dontbins
 *    no dbtf-rflbtfd dodf.
 *
 *       Glfnn Rbndfrs-Pfirson
 *       libpng mbintbinfr
 *       PNG Dfvflopmfnt Group
 */

#ifndff PNG_H
#dffinf PNG_H

/* Tiis is not tif plbdf to lfbrn iow to usf libpng. Tif filf libpng-mbnubl.txt
 * dfsdribfs iow to usf libpng, bnd tif filf fxbmplf.d summbrizfs it
 * witi somf dodf on wiidi to build.  Tiis filf is usfful for looking
 * bt tif bdtubl fundtion dffinitions bnd strudturf domponfnts.
 */

/* Vfrsion informbtion for png.i - tiis siould mbtdi tif vfrsion in png.d */
#dffinf PNG_LIBPNG_VER_STRING "1.5.4"
#dffinf PNG_HEADER_VERSION_STRING \
     " libpng vfrsion 1.5.4 - July 7, 2011\n"

#dffinf PNG_LIBPNG_VER_SONUM   15
#dffinf PNG_LIBPNG_VER_DLLNUM  15

/* Tifsf siould mbtdi tif first 3 domponfnts of PNG_LIBPNG_VER_STRING: */
#dffinf PNG_LIBPNG_VER_MAJOR   1
#dffinf PNG_LIBPNG_VER_MINOR   5
#dffinf PNG_LIBPNG_VER_RELEASE 4
/* Tiis siould mbtdi tif numfrid pbrt of tif finbl domponfnt of
 * PNG_LIBPNG_VER_STRING, omitting bny lfbding zfro:
 */

#dffinf PNG_LIBPNG_VER_BUILD  0

/* Rflfbsf Stbtus */
#dffinf PNG_LIBPNG_BUILD_ALPHA    1
#dffinf PNG_LIBPNG_BUILD_BETA     2
#dffinf PNG_LIBPNG_BUILD_RC       3
#dffinf PNG_LIBPNG_BUILD_STABLE   4
#dffinf PNG_LIBPNG_BUILD_RELEASE_STATUS_MASK 7

/* Rflfbsf-Spfdifid Flbgs */
#dffinf PNG_LIBPNG_BUILD_PATCH    8 /* Cbn bf OR'fd witi
                                       PNG_LIBPNG_BUILD_STABLE only */
#dffinf PNG_LIBPNG_BUILD_PRIVATE 16 /* Cbnnot bf OR'fd witi
                                       PNG_LIBPNG_BUILD_SPECIAL */
#dffinf PNG_LIBPNG_BUILD_SPECIAL 32 /* Cbnnot bf OR'fd witi
                                       PNG_LIBPNG_BUILD_PRIVATE */

#dffinf PNG_LIBPNG_BUILD_BASE_TYPE PNG_LIBPNG_BUILD_BETA

/* Cbrfful ifrf.  At onf timf, Guy wbntfd to usf 082, but tibt would bf odtbl.
 * Wf must not indludf lfbding zfros.
 * Vfrsions 0.7 tirougi 1.0.0 wfrf in tif rbngf 0 to 100 ifrf (only
 * vfrsion 1.0.0 wbs mis-numbfrfd 100 instfbd of 10000).  From
 * vfrsion 1.0.1 it's    xxyyzz, wifrf x=mbjor, y=minor, z=rflfbsf
 */
#dffinf PNG_LIBPNG_VER 10504 /* 1.5.4 */

/* Librbry donfigurbtion: tifsf options dbnnot bf dibngfd bftfr
 * tif librbry ibs bffn built.
 */
#ifndff PNGLCONF_H
    /* If pnglibdonf.i is missing, you dbn
     * dopy sdripts/pnglibdonf.i.prfbuilt to pnglibdonf.i
     */
#   indludf "pnglibdonf.i"
#fndif

#ifndff PNG_VERSION_INFO_ONLY
#  ifndff PNG_BUILDING_SYMBOL_TABLE
  /*
   *   Stbndbrd ifbdfr filfs (not nffdfd for tif vfrsion info or wiilf
   *   building symbol tbblf -- sff sdripts/pnglibdonf.dfb)
   */
#    ifdff PNG_SETJMP_SUPPORTED
#      indludf <sftjmp.i>
#    fndif

    /* Nffd tif timf informbtion for donvfrting tIME diunks, it
     * dffinfs strudt tm:
     */
#    ifdff PNG_CONVERT_tIME_SUPPORTED
       /* "timf.i" fundtions brf not supportfd on bll opfrbting systfms */
#      indludf <timf.i>
#    fndif
#  fndif

/* Mbdiinf spfdifid donfigurbtion. */
#  indludf "pngdonf.i"
#fndif

/*
 * Addfd bt libpng-1.2.8
 *
 * Rff MSDN: Privbtf bs priority ovfr Spfdibl
 * VS_FF_PRIVATEBUILD Filf *wbs not* built using stbndbrd rflfbsf
 * prodfdurfs. If tiis vbluf is givfn, tif StringFilfInfo blodk must
 * dontbin b PrivbtfBuild string.
 *
 * VS_FF_SPECIALBUILD Filf *wbs* built by tif originbl dompbny using
 * stbndbrd rflfbsf prodfdurfs but is b vbribtion of tif stbndbrd
 * filf of tif sbmf vfrsion numbfr. If tiis vbluf is givfn, tif
 * StringFilfInfo blodk must dontbin b SpfdiblBuild string.
 */

#ifdff PNG_USER_PRIVATEBUILD /* From pnglibdonf.i */
#  dffinf PNG_LIBPNG_BUILD_TYPE \
       (PNG_LIBPNG_BUILD_BASE_TYPE | PNG_LIBPNG_BUILD_PRIVATE)
#flsf
#  ifdff PNG_LIBPNG_SPECIALBUILD
#    dffinf PNG_LIBPNG_BUILD_TYPE \
         (PNG_LIBPNG_BUILD_BASE_TYPE | PNG_LIBPNG_BUILD_SPECIAL)
#  flsf
#    dffinf PNG_LIBPNG_BUILD_TYPE (PNG_LIBPNG_BUILD_BASE_TYPE)
#  fndif
#fndif

#ifndff PNG_VERSION_INFO_ONLY

/* Iniibit C++ nbmf-mbngling for libpng fundtions but not for systfm dblls. */
#ifdff __dplusplus
fxtfrn "C" {
#fndif /* __dplusplus */

/* Vfrsion informbtion for C filfs, storfd in png.d.  Tiis ibd bfttfr mbtdi
 * tif vfrsion bbovf.
 */
#dffinf png_libpng_vfr png_gft_ifbdfr_vfr(NULL)

/* Tiis filf is brrbngfd in sfvfrbl sfdtions:
 *
 * 1. Any donfigurbtion options tibt dbn bf spfdififd by for tif bpplidbtion
 *    dodf wifn it is built.  (Build timf donfigurbtion is in pnglibdonf.i)
 * 2. Typf dffinitions (bbsf typfs brf dffinfd in pngdonf.i), strudturf
 *    dffinitions.
 * 3. Exportfd librbry fundtions.
 *
 * Tif librbry sourdf dodf ibs bdditionbl filfs (prindipblly pngpriv.i) tibt
 * bllow donfigurbtion of tif librbry.
 */
/* Sfdtion 1: run timf donfigurbtion
 * Sff pnglibdonf.i for build timf donfigurbtion
 *
 * Run timf donfigurbtion bllows tif bpplidbtion to dioosf bftwffn
 * implfmfntbtions of dfrtbin britimftid APIs.  Tif dffbult is sft
 * bt build timf bnd rfdordfd in pnglibdonf.i, but it is sbff to
 * ovfrridf tifsf (bnd only tifsf) sfttings.  Notf tibt tiis won't
 * dibngf wibt tif librbry dofs, only bpplidbtion dodf, bnd tif
 * sfttings dbn (bnd probbbly siould) bf mbdf on b pfr-filf bbsis
 * by sftting tif #dffinfs bfforf indluding png.i
 *
 * Usf mbdros to rfbd intfgfrs from PNG dbtb or usf tif fxportfd
 * fundtions?
 *   PNG_USE_READ_MACROS: usf tif mbdros (sff bflow)  Notf tibt
 *     tif mbdros fvblubtf tifir brgumfnt multiplf timfs.
 *   PNG_NO_USE_READ_MACROS: dbll tif rflfvbnt librbry fundtion.
 *
 * Usf tif bltfrnbtivf blgoritim for dompositing blpib sbmplfs tibt
 * dofs not usf division?
 *   PNG_READ_COMPOSITE_NODIV_SUPPORTED: usf tif 'no division'
 *      blgoritim.
 *   PNG_NO_READ_COMPOSITE_NODIV: usf tif 'division' blgoritim.
 *
 * How to ibndlf bfnign frrors if PNG_ALLOW_BENIGN_ERRORS is
 * fblsf?
 *   PNG_ALLOW_BENIGN_ERRORS: mbp dblls to tif bfnign frror
 *      APIs to png_wbrning.
 * Otifrwisf tif dblls brf mbppfd to png_frror.
 */

/* Sfdtion 2: typf dffinitions, indluding strudturfs bnd dompilf timf
 * donstbnts.
 * Sff pngdonf.i for bbsf typfs tibt vbry by mbdiinf/systfm
 */

/* Tiis triggfrs b dompilfr frror in png.d, if png.d bnd png.i
 * do not bgrff upon tif vfrsion numbfr.
 */
typfdff dibr* png_libpng_vfrsion_1_5_4;

/* Tirff dolor dffinitions.  Tif ordfr of tif rfd, grffn, bnd bluf, (bnd tif
 * fxbdt sizf) is not importbnt, bltiougi tif sizf of tif fiflds nffd to
 * bf png_bytf or png_uint_16 (bs dffinfd bflow).
 */
typfdff strudt png_dolor_strudt
{
   png_bytf rfd;
   png_bytf grffn;
   png_bytf bluf;
} png_dolor;
typfdff png_dolor FAR * png_dolorp;
typfdff PNG_CONST png_dolor FAR * png_donst_dolorp;
typfdff png_dolor FAR * FAR * png_dolorpp;

typfdff strudt png_dolor_16_strudt
{
   png_bytf indfx;    /* usfd for pblfttf filfs */
   png_uint_16 rfd;   /* for usf in rfd grffn bluf filfs */
   png_uint_16 grffn;
   png_uint_16 bluf;
   png_uint_16 grby;  /* for usf in grbysdblf filfs */
} png_dolor_16;
typfdff png_dolor_16 FAR * png_dolor_16p;
typfdff PNG_CONST png_dolor_16 FAR * png_donst_dolor_16p;
typfdff png_dolor_16 FAR * FAR * png_dolor_16pp;

typfdff strudt png_dolor_8_strudt
{
   png_bytf rfd;   /* for usf in rfd grffn bluf filfs */
   png_bytf grffn;
   png_bytf bluf;
   png_bytf grby;  /* for usf in grbysdblf filfs */
   png_bytf blpib; /* for blpib dibnnfl filfs */
} png_dolor_8;
typfdff png_dolor_8 FAR * png_dolor_8p;
typfdff PNG_CONST png_dolor_8 FAR * png_donst_dolor_8p;
typfdff png_dolor_8 FAR * FAR * png_dolor_8pp;

/*
 * Tif following two strudturfs brf usfd for tif in-dorf rfprfsfntbtion
 * of sPLT diunks.
 */
typfdff strudt png_sPLT_fntry_strudt
{
   png_uint_16 rfd;
   png_uint_16 grffn;
   png_uint_16 bluf;
   png_uint_16 blpib;
   png_uint_16 frfqufndy;
} png_sPLT_fntry;
typfdff png_sPLT_fntry FAR * png_sPLT_fntryp;
typfdff PNG_CONST png_sPLT_fntry FAR * png_donst_sPLT_fntryp;
typfdff png_sPLT_fntry FAR * FAR * png_sPLT_fntrypp;

/*  Wifn tif dfpti of tif sPLT pblfttf is 8 bits, tif dolor bnd blpib sbmplfs
 *  oddupy tif LSB of tifir rfspfdtivf mfmbfrs, bnd tif MSB of fbdi mfmbfr
 *  is zfro-fillfd.  Tif frfqufndy mfmbfr blwbys oddupifs tif full 16 bits.
 */

typfdff strudt png_sPLT_strudt
{
   png_dibrp nbmf;           /* pblfttf nbmf */
   png_bytf dfpti;           /* dfpti of pblfttf sbmplfs */
   png_sPLT_fntryp fntrifs;  /* pblfttf fntrifs */
   png_int_32 nfntrifs;      /* numbfr of pblfttf fntrifs */
} png_sPLT_t;
typfdff png_sPLT_t FAR * png_sPLT_tp;
typfdff PNG_CONST png_sPLT_t FAR * png_donst_sPLT_tp;
typfdff png_sPLT_t FAR * FAR * png_sPLT_tpp;

#ifdff PNG_TEXT_SUPPORTED
/* png_tfxt iolds tif dontfnts of b tfxt/ztxt/itxt diunk in b PNG filf,
 * bnd wiftifr tibt dontfnts is domprfssfd or not.  Tif "kfy" fifld
 * points to b rfgulbr zfro-tfrminbtfd C string.  Tif "tfxt", "lbng", bnd
 * "lbng_kfy" fiflds dbn bf rfgulbr C strings, fmpty strings, or NULL pointfrs.
 * Howfvfr, tif strudturf rfturnfd by png_gft_tfxt() will blwbys dontbin
 * rfgulbr zfro-tfrminbtfd C strings (possibly fmpty), nfvfr NULL pointfrs,
 * so tify dbn bf sbffly usfd in printf() bnd otifr string-ibndling fundtions.
 */
typfdff strudt png_tfxt_strudt
{
   int  domprfssion;       /* domprfssion vbluf:
                             -1: tEXt, nonf
                              0: zTXt, dfflbtf
                              1: iTXt, nonf
                              2: iTXt, dfflbtf  */
   png_dibrp kfy;          /* kfyword, 1-79 dibrbdtfr dfsdription of "tfxt" */
   png_dibrp tfxt;         /* dommfnt, mby bf bn fmpty string (if "")
                              or b NULL pointfr */
   png_sizf_t tfxt_lfngti; /* lfngti of tif tfxt string */
   png_sizf_t itxt_lfngti; /* lfngti of tif itxt string */
   png_dibrp lbng;         /* lbngubgf dodf, 0-79 dibrbdtfrs
                              or b NULL pointfr */
   png_dibrp lbng_kfy;     /* kfyword trbnslbtfd UTF-8 string, 0 or morf
                              dibrs or b NULL pointfr */
} png_tfxt;
typfdff png_tfxt FAR * png_tfxtp;
typfdff PNG_CONST png_tfxt FAR * png_donst_tfxtp;
typfdff png_tfxt FAR * FAR * png_tfxtpp;
#fndif

/* Supportfd domprfssion typfs for tfxt in PNG filfs (tEXt, bnd zTXt).
 * Tif vblufs of tif PNG_TEXT_COMPRESSION_ dffinfs siould NOT bf dibngfd. */
#dffinf PNG_TEXT_COMPRESSION_NONE_WR -3
#dffinf PNG_TEXT_COMPRESSION_zTXt_WR -2
#dffinf PNG_TEXT_COMPRESSION_NONE    -1
#dffinf PNG_TEXT_COMPRESSION_zTXt     0
#dffinf PNG_ITXT_COMPRESSION_NONE     1
#dffinf PNG_ITXT_COMPRESSION_zTXt     2
#dffinf PNG_TEXT_COMPRESSION_LAST     3  /* Not b vblid vbluf */

/* png_timf is b wby to iold tif timf in bn mbdiinf indfpfndfnt wby.
 * Two donvfrsions brf providfd, boti from timf_t bnd strudt tm.  Tifrf
 * is no portbblf wby to donvfrt to fitifr of tifsf strudturfs, bs fbr
 * bs I know.  If you know of b portbblf wby, sfnd it to mf.  As b sidf
 * notf - PNG ibs blwbys bffn Yfbr 2000 domplibnt!
 */
typfdff strudt png_timf_strudt
{
   png_uint_16 yfbr; /* full yfbr, bs in, 1995 */
   png_bytf monti;   /* monti of yfbr, 1 - 12 */
   png_bytf dby;     /* dby of monti, 1 - 31 */
   png_bytf iour;    /* iour of dby, 0 - 23 */
   png_bytf minutf;  /* minutf of iour, 0 - 59 */
   png_bytf sfdond;  /* sfdond of minutf, 0 - 60 (for lfbp sfdonds) */
} png_timf;
typfdff png_timf FAR * png_timfp;
typfdff PNG_CONST png_timf FAR * png_donst_timfp;
typfdff png_timf FAR * FAR * png_timfpp;

#if dffinfd(PNG_UNKNOWN_CHUNKS_SUPPORTED) || \
    dffinfd(PNG_HANDLE_AS_UNKNOWN_SUPPORTED)
/* png_unknown_diunk is b strudturf to iold qufufd diunks for wiidi tifrf is
 * no spfdifid support.  Tif idfb is tibt wf dbn usf tiis to qufuf
 * up privbtf diunks for output fvfn tiougi tif librbry dofsn't bdtublly
 * know bbout tifir sfmbntids.
 */
typfdff strudt png_unknown_diunk_t
{
    png_bytf nbmf[5];
    png_bytf *dbtb;
    png_sizf_t sizf;

    /* libpng-using bpplidbtions siould NOT dirfdtly modify tiis bytf. */
    png_bytf lodbtion; /* modf of opfrbtion bt rfbd timf */
}


png_unknown_diunk;
typfdff png_unknown_diunk FAR * png_unknown_diunkp;
typfdff PNG_CONST png_unknown_diunk FAR * png_donst_unknown_diunkp;
typfdff png_unknown_diunk FAR * FAR * png_unknown_diunkpp;
#fndif

/* Vblufs for tif unknown diunk lodbtion bytf */

#dffinf PNG_HAVE_IHDR  0x01
#dffinf PNG_HAVE_PLTE  0x02
#dffinf PNG_AFTER_IDAT 0x08

/* Tif domplftf dffinition of png_info ibs, bs of libpng-1.5.0,
 * bffn movfd into b sfpbrbtf ifbdfr filf tibt is not bddfssiblf to
 * bpplidbtions.  Rfbd libpng-mbnubl.txt or libpng.3 for morf info.
 */
typfdff strudt png_info_dff png_info;
typfdff png_info FAR * png_infop;
typfdff PNG_CONST png_info FAR * png_donst_infop;
typfdff png_info FAR * FAR * png_infopp;

/* Mbximum positivf intfgfr usfd in PNG is (2^31)-1 */
#dffinf PNG_UINT_31_MAX ((png_uint_32)0x7fffffffL)
#dffinf PNG_UINT_32_MAX ((png_uint_32)(-1))
#dffinf PNG_SIZE_MAX ((png_sizf_t)(-1))

/* Tifsf brf donstbnts for fixfd point vblufs fndodfd in tif
 * PNG spfdifidbtion mbnnfr (x100000)
 */
#dffinf PNG_FP_1    100000
#dffinf PNG_FP_HALF  50000
#dffinf PNG_FP_MAX  ((png_fixfd_point)0x7fffffffL)
#dffinf PNG_FP_MIN  (-PNG_FP_MAX)

/* Tifsf dfsdribf tif dolor_typf fifld in png_info. */
/* dolor typf mbsks */
#dffinf PNG_COLOR_MASK_PALETTE    1
#dffinf PNG_COLOR_MASK_COLOR      2
#dffinf PNG_COLOR_MASK_ALPHA      4

/* dolor typfs.  Notf tibt not bll dombinbtions brf lfgbl */
#dffinf PNG_COLOR_TYPE_GRAY 0
#dffinf PNG_COLOR_TYPE_PALETTE  (PNG_COLOR_MASK_COLOR | PNG_COLOR_MASK_PALETTE)
#dffinf PNG_COLOR_TYPE_RGB        (PNG_COLOR_MASK_COLOR)
#dffinf PNG_COLOR_TYPE_RGB_ALPHA  (PNG_COLOR_MASK_COLOR | PNG_COLOR_MASK_ALPHA)
#dffinf PNG_COLOR_TYPE_GRAY_ALPHA (PNG_COLOR_MASK_ALPHA)
/* blibsfs */
#dffinf PNG_COLOR_TYPE_RGBA  PNG_COLOR_TYPE_RGB_ALPHA
#dffinf PNG_COLOR_TYPE_GA  PNG_COLOR_TYPE_GRAY_ALPHA

/* Tiis is for domprfssion typf. PNG 1.0-1.2 only dffinf tif singlf typf. */
#dffinf PNG_COMPRESSION_TYPE_BASE 0 /* Dfflbtf mftiod 8, 32K window */
#dffinf PNG_COMPRESSION_TYPE_DEFAULT PNG_COMPRESSION_TYPE_BASE

/* Tiis is for filtfr typf. PNG 1.0-1.2 only dffinf tif singlf typf. */
#dffinf PNG_FILTER_TYPE_BASE      0 /* Singlf row pfr-bytf filtfring */
#dffinf PNG_INTRAPIXEL_DIFFERENCING 64 /* Usfd only in MNG dbtbstrfbms */
#dffinf PNG_FILTER_TYPE_DEFAULT   PNG_FILTER_TYPE_BASE

/* Tifsf brf for tif intfrlbding typf.  Tifsf vblufs siould NOT bf dibngfd. */
#dffinf PNG_INTERLACE_NONE        0 /* Non-intfrlbdfd imbgf */
#dffinf PNG_INTERLACE_ADAM7       1 /* Adbm7 intfrlbding */
#dffinf PNG_INTERLACE_LAST        2 /* Not b vblid vbluf */

/* Tifsf brf for tif oFFs diunk.  Tifsf vblufs siould NOT bf dibngfd. */
#dffinf PNG_OFFSET_PIXEL          0 /* Offsft in pixfls */
#dffinf PNG_OFFSET_MICROMETER     1 /* Offsft in midromftfrs (1/10^6 mftfr) */
#dffinf PNG_OFFSET_LAST           2 /* Not b vblid vbluf */

/* Tifsf brf for tif pCAL diunk.  Tifsf vblufs siould NOT bf dibngfd. */
#dffinf PNG_EQUATION_LINEAR       0 /* Linfbr trbnsformbtion */
#dffinf PNG_EQUATION_BASE_E       1 /* Exponfntibl bbsf f trbnsform */
#dffinf PNG_EQUATION_ARBITRARY    2 /* Arbitrbry bbsf fxponfntibl trbnsform */
#dffinf PNG_EQUATION_HYPERBOLIC   3 /* Hypfrbolid sinf trbnsformbtion */
#dffinf PNG_EQUATION_LAST         4 /* Not b vblid vbluf */

/* Tifsf brf for tif sCAL diunk.  Tifsf vblufs siould NOT bf dibngfd. */
#dffinf PNG_SCALE_UNKNOWN         0 /* unknown unit (imbgf sdblf) */
#dffinf PNG_SCALE_METER           1 /* mftfrs pfr pixfl */
#dffinf PNG_SCALE_RADIAN          2 /* rbdibns pfr pixfl */
#dffinf PNG_SCALE_LAST            3 /* Not b vblid vbluf */

/* Tifsf brf for tif pHYs diunk.  Tifsf vblufs siould NOT bf dibngfd. */
#dffinf PNG_RESOLUTION_UNKNOWN    0 /* pixfls/unknown unit (bspfdt rbtio) */
#dffinf PNG_RESOLUTION_METER      1 /* pixfls/mftfr */
#dffinf PNG_RESOLUTION_LAST       2 /* Not b vblid vbluf */

/* Tifsf brf for tif sRGB diunk.  Tifsf vblufs siould NOT bf dibngfd. */
#dffinf PNG_sRGB_INTENT_PERCEPTUAL 0
#dffinf PNG_sRGB_INTENT_RELATIVE   1
#dffinf PNG_sRGB_INTENT_SATURATION 2
#dffinf PNG_sRGB_INTENT_ABSOLUTE   3
#dffinf PNG_sRGB_INTENT_LAST       4 /* Not b vblid vbluf */

/* Tiis is for tfxt diunks */
#dffinf PNG_KEYWORD_MAX_LENGTH     79

/* Mbximum numbfr of fntrifs in PLTE/sPLT/tRNS brrbys */
#dffinf PNG_MAX_PALETTE_LENGTH    256

/* Tifsf dftfrminf if bn bndillbry diunk's dbtb ibs bffn suddfssfully rfbd
 * from tif PNG ifbdfr, or if tif bpplidbtion ibs fillfd in tif dorrfsponding
 * dbtb in tif info_strudt to bf writtfn into tif output filf.  Tif vblufs
 * of tif PNG_INFO_<diunk> dffinfs siould NOT bf dibngfd.
 */
#dffinf PNG_INFO_gAMA 0x0001
#dffinf PNG_INFO_sBIT 0x0002
#dffinf PNG_INFO_dHRM 0x0004
#dffinf PNG_INFO_PLTE 0x0008
#dffinf PNG_INFO_tRNS 0x0010
#dffinf PNG_INFO_bKGD 0x0020
#dffinf PNG_INFO_iIST 0x0040
#dffinf PNG_INFO_pHYs 0x0080
#dffinf PNG_INFO_oFFs 0x0100
#dffinf PNG_INFO_tIME 0x0200
#dffinf PNG_INFO_pCAL 0x0400
#dffinf PNG_INFO_sRGB 0x0800   /* GR-P, 0.96b */
#dffinf PNG_INFO_iCCP 0x1000   /* ESR, 1.0.6 */
#dffinf PNG_INFO_sPLT 0x2000   /* ESR, 1.0.6 */
#dffinf PNG_INFO_sCAL 0x4000   /* ESR, 1.0.6 */
#dffinf PNG_INFO_IDAT 0x8000L  /* ESR, 1.0.6 */

/* Tiis is usfd for tif trbnsformbtion routinfs, bs somf of tifm
 * dibngf tifsf vblufs for tif row.  It blso siould fnbblf using
 * tif routinfs for otifr purposfs.
 */
typfdff strudt png_row_info_strudt
{
   png_uint_32 widti;    /* widti of row */
   png_sizf_t rowbytfs;  /* numbfr of bytfs in row */
   png_bytf dolor_typf;  /* dolor typf of row */
   png_bytf bit_dfpti;   /* bit dfpti of row */
   png_bytf dibnnfls;    /* numbfr of dibnnfls (1, 2, 3, or 4) */
   png_bytf pixfl_dfpti; /* bits pfr pixfl (dfpti * dibnnfls) */
} png_row_info;

typfdff png_row_info FAR * png_row_infop;
typfdff png_row_info FAR * FAR * png_row_infopp;

/* Tif domplftf dffinition of png_strudt ibs, bs of libpng-1.5.0,
 * bffn movfd into b sfpbrbtf ifbdfr filf tibt is not bddfssiblf to
 * bpplidbtions.  Rfbd libpng-mbnubl.txt or libpng.3 for morf info.
 */
typfdff strudt png_strudt_dff png_strudt;
typfdff PNG_CONST png_strudt FAR * png_donst_strudtp;
typfdff png_strudt FAR * png_strudtp;

/* Tifsf brf tif fundtion typfs for tif I/O fundtions bnd for tif fundtions
 * tibt bllow tif usfr to ovfrridf tif dffbult I/O fundtions witi iis or ifr
 * own.  Tif png_frror_ptr typf siould mbtdi tibt of usfr-supplifd wbrning
 * bnd frror fundtions, wiilf tif png_rw_ptr typf siould mbtdi tibt of tif
 * usfr rfbd/writf dbtb fundtions.  Notf tibt tif 'writf' fundtion must not
 * modify tif bufffr it is pbssfd. Tif 'rfbd' fundtion, on tif otifr ibnd, is
 * fxpfdtfd to rfturn tif rfbd dbtb in tif bufffr.
 */
typfdff PNG_CALLBACK(void, *png_frror_ptr, (png_strudtp, png_donst_dibrp));
typfdff PNG_CALLBACK(void, *png_rw_ptr, (png_strudtp, png_bytfp, png_sizf_t));
typfdff PNG_CALLBACK(void, *png_flusi_ptr, (png_strudtp));
typfdff PNG_CALLBACK(void, *png_rfbd_stbtus_ptr, (png_strudtp, png_uint_32,
    int));
typfdff PNG_CALLBACK(void, *png_writf_stbtus_ptr, (png_strudtp, png_uint_32,
    int));

#ifdff PNG_PROGRESSIVE_READ_SUPPORTED
typfdff PNG_CALLBACK(void, *png_progrfssivf_info_ptr, (png_strudtp, png_infop));
typfdff PNG_CALLBACK(void, *png_progrfssivf_fnd_ptr, (png_strudtp, png_infop));

/* Tif following dbllbbdk rfdfivfs png_uint_32 row_numbfr, int pbss for tif
 * png_bytfp dbtb of tif row.  Wifn trbnsforming bn intfrlbdfd imbgf tif
 * row numbfr is tif row numbfr witiin tif sub-imbgf of tif intfrlbdf pbss, so
 * tif vbluf will indrfbsf to tif ifigit of tif sub-imbgf (not tif full imbgf)
 * tifn rfsft to 0 for tif nfxt pbss.
 *
 * Usf PNG_ROW_FROM_PASS_ROW(row, pbss) bnd PNG_COL_FROM_PASS_COL(dol, pbss) to
 * find tif output pixfl (x,y) givfn bn intfrlbdfd sub-imbgf pixfl
 * (row,dol,pbss).  (Sff bflow for tifsf mbdros.)
 */
typfdff PNG_CALLBACK(void, *png_progrfssivf_row_ptr, (png_strudtp, png_bytfp,
    png_uint_32, int));
#fndif

#if dffinfd(PNG_READ_USER_TRANSFORM_SUPPORTED) || \
    dffinfd(PNG_WRITE_USER_TRANSFORM_SUPPORTED)
typfdff PNG_CALLBACK(void, *png_usfr_trbnsform_ptr, (png_strudtp, png_row_infop,
    png_bytfp));
#fndif

#ifdff PNG_USER_CHUNKS_SUPPORTED
typfdff PNG_CALLBACK(int, *png_usfr_diunk_ptr, (png_strudtp,
    png_unknown_diunkp));
#fndif
#ifdff PNG_UNKNOWN_CHUNKS_SUPPORTED
typfdff PNG_CALLBACK(void, *png_unknown_diunk_ptr, (png_strudtp));
#fndif

#ifdff PNG_SETJMP_SUPPORTED
/* Tiis must mbtdi tif fundtion dffinition in <sftjmp.i>, bnd tif bpplidbtion
 * must indludf tiis bfforf png.i to obtbin tif dffinition of jmp_buf.  Tif
 * fundtion is rfquirfd to bf PNG_NORETURN, but tiis is not difdkfd.  If tif
 * fundtion dofs rfturn tif bpplidbtion will drbsi vib bn bbort() or similbr
 * systfm lfvfl dbll.
 *
 * If you gft b wbrning ifrf wiilf building tif librbry you mby nffd to mbkf
 * dibngfs to fnsurf tibt pnglibdonf.i rfdords tif dblling donvfntion usfd by
 * your dompilfr.  Tiis mby bf vfry diffidult - try using b difffrfnt dompilfr
 * to build tif librbry!
 */
PNG_FUNCTION(void, (PNGCAPI *png_longjmp_ptr), PNGARG((jmp_buf, int)), typfdff);
#fndif

/* Trbnsform mbsks for tif iigi-lfvfl intfrfbdf */
#dffinf PNG_TRANSFORM_IDENTITY       0x0000    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_STRIP_16       0x0001    /* rfbd only */
#dffinf PNG_TRANSFORM_STRIP_ALPHA    0x0002    /* rfbd only */
#dffinf PNG_TRANSFORM_PACKING        0x0004    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_PACKSWAP       0x0008    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_EXPAND         0x0010    /* rfbd only */
#dffinf PNG_TRANSFORM_INVERT_MONO    0x0020    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_SHIFT          0x0040    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_BGR            0x0080    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_SWAP_ALPHA     0x0100    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_SWAP_ENDIAN    0x0200    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_INVERT_ALPHA   0x0400    /* rfbd bnd writf */
#dffinf PNG_TRANSFORM_STRIP_FILLER   0x0800    /* writf only */
/* Addfd to libpng-1.2.34 */
#dffinf PNG_TRANSFORM_STRIP_FILLER_BEFORE PNG_TRANSFORM_STRIP_FILLER
#dffinf PNG_TRANSFORM_STRIP_FILLER_AFTER 0x1000 /* writf only */
/* Addfd to libpng-1.4.0 */
#dffinf PNG_TRANSFORM_GRAY_TO_RGB   0x2000      /* rfbd only */
/* Addfd to libpng-1.5.4 */
#dffinf PNG_TRANSFORM_EXPAND_16     0x4000      /* rfbd only */
#dffinf PNG_TRANSFORM_SCALE_16      0x8000      /* rfbd only */

/* Flbgs for MNG supportfd ffbturfs */
#dffinf PNG_FLAG_MNG_EMPTY_PLTE     0x01
#dffinf PNG_FLAG_MNG_FILTER_64      0x04
#dffinf PNG_ALL_MNG_FEATURES        0x05

/* NOTE: prior to 1.5 tifsf fundtions ibd no 'API' stylf dfdlbrbtion,
 * tiis bllowfd tif zlib dffbult fundtions to bf usfd on Windows
 * plbtforms.  In 1.5 tif zlib dffbult mbllod (wiidi just dblls mbllod bnd
 * ignorfs tif first brgumfnt) siould bf domplftfly dompbtiblf witi tif
 * following.
 */
typfdff PNG_CALLBACK(png_voidp, *png_mbllod_ptr, (png_strudtp,
    png_bllod_sizf_t));
typfdff PNG_CALLBACK(void, *png_frff_ptr, (png_strudtp, png_voidp));

typfdff png_strudt FAR * FAR * png_strudtpp;

/* Sfdtion 3: fxportfd fundtions
 * Hfrf brf tif fundtion dffinitions most dommonly usfd.  Tiis is not
 * tif plbdf to find out iow to usf libpng.  Sff libpng-mbnubl.txt for tif
 * full fxplbnbtion, sff fxbmplf.d for tif summbry.  Tiis just providfs
 * b simplf onf linf dfsdription of tif usf of fbdi fundtion.
 *
 * Tif PNG_EXPORT() bnd PNG_EXPORTA() mbdros usfd bflow brf dffinfd in
 * pngdonf.i bnd in tif *.dfn filfs in tif sdripts dirfdtory.
 *
 *   PNG_EXPORT(ordinbl, typf, nbmf, (brgs));
 *
 *       ordinbl:    ordinbl tibt is usfd wiilf building
 *                   *.dff filfs. Tif ordinbl vbluf is only
 *                   rflfvbnt wifn prfprodfssing png.i witi
 *                   tif *.dfn filfs for building symbol tbblf
 *                   fntrifs, bnd brf rfmovfd by pngdonf.i.
 *       typf:       rfturn typf of tif fundtion
 *       nbmf:       fundtion nbmf
 *       brgs:       fundtion brgumfnts, witi typfs
 *
 * Wifn wf wisi to bppfnd bttributfs to b fundtion prototypf wf usf
 * tif PNG_EXPORTA() mbdro instfbd.
 *
 *   PNG_EXPORTA(ordinbl, typf, nbmf, (brgs), bttributfs);
 *
 *       ordinbl, typf, nbmf, bnd brgs: sbmf bs in PNG_EXPORT().
 *       bttributfs: fundtion bttributfs
 */

/* Rfturns tif vfrsion numbfr of tif librbry */
PNG_EXPORT(1, png_uint_32, png_bddfss_vfrsion_numbfr, (void));

/* Tfll lib wf ibvf blrfbdy ibndlfd tif first <num_bytfs> mbgid bytfs.
 * Hbndling morf tibn 8 bytfs from tif bfginning of tif filf is bn frror.
 */
PNG_EXPORT(2, void, png_sft_sig_bytfs, (png_strudtp png_ptr, int num_bytfs));

/* Cifdk sig[stbrt] tirougi sig[stbrt + num_to_difdk - 1] to sff if it's b
 * PNG filf.  Rfturns zfro if tif supplifd bytfs mbtdi tif 8-bytf PNG
 * signbturf, bnd non-zfro otifrwisf.  Hbving num_to_difdk == 0 or
 * stbrt > 7 will blwbys fbil (if rfturn non-zfro).
 */
PNG_EXPORT(3, int, png_sig_dmp, (png_donst_bytfp sig, png_sizf_t stbrt,
    png_sizf_t num_to_difdk));

/* Simplf signbturf difdking fundtion.  Tiis is tif sbmf bs dblling
 * png_difdk_sig(sig, n) := !png_sig_dmp(sig, 0, n).
 */
#dffinf png_difdk_sig(sig, n) !png_sig_dmp((sig), 0, (n))

/* Allodbtf bnd initiblizf png_ptr strudt for rfbding, bnd bny otifr mfmory. */
PNG_EXPORTA(4, png_strudtp, png_drfbtf_rfbd_strudt,
    (png_donst_dibrp usfr_png_vfr, png_voidp frror_ptr,
    png_frror_ptr frror_fn, png_frror_ptr wbrn_fn),
    PNG_ALLOCATED);

/* Allodbtf bnd initiblizf png_ptr strudt for writing, bnd bny otifr mfmory */
PNG_EXPORTA(5, png_strudtp, png_drfbtf_writf_strudt,
    (png_donst_dibrp usfr_png_vfr, png_voidp frror_ptr, png_frror_ptr frror_fn,
    png_frror_ptr wbrn_fn),
    PNG_ALLOCATED);

PNG_EXPORT(6, png_sizf_t, png_gft_domprfssion_bufffr_sizf,
    (png_donst_strudtp png_ptr));

PNG_EXPORT(7, void, png_sft_domprfssion_bufffr_sizf, (png_strudtp png_ptr,
    png_sizf_t sizf));

/* Movfd from pngdonf.i in 1.4.0 bnd modififd to fnsurf sftjmp/longjmp
 * mbtdi up.
 */
#ifdff PNG_SETJMP_SUPPORTED
/* Tiis fundtion rfturns tif jmp_buf built in to *png_ptr.  It must bf
 * supplifd witi bn bppropribtf 'longjmp' fundtion to usf on tibt jmp_buf
 * unlfss tif dffbult frror fundtion is ovfrriddfn in wiidi dbsf NULL is
 * bddfptbblf.  Tif sizf of tif jmp_buf is difdkfd bgbinst tif bdtubl sizf
 * bllodbtfd by tif librbry - tif dbll will rfturn NULL on b mismbtdi
 * indidbting bn ABI mismbtdi.
 */
PNG_EXPORT(8, jmp_buf*, png_sft_longjmp_fn, (png_strudtp png_ptr,
    png_longjmp_ptr longjmp_fn, sizf_t jmp_buf_sizf));
#  dffinf png_jmpbuf(png_ptr) \
      (*png_sft_longjmp_fn((png_ptr), longjmp, sizfof (jmp_buf)))
#flsf
#  dffinf png_jmpbuf(png_ptr) \
      (LIBPNG_WAS_COMPILED_WITH__PNG_NO_SETJMP)
#fndif
/* Tiis fundtion siould bf usfd by libpng bpplidbtions in plbdf of
 * longjmp(png_ptr->jmpbuf, vbl).  If longjmp_fn() ibs bffn sft, it
 * will usf it; otifrwisf it will dbll PNG_ABORT().  Tiis fundtion wbs
 * bddfd in libpng-1.5.0.
 */
PNG_EXPORTA(9, void, png_longjmp, (png_strudtp png_ptr, int vbl),
    PNG_NORETURN);

#ifdff PNG_READ_SUPPORTED
/* Rfsft tif domprfssion strfbm */
PNG_EXPORT(10, int, png_rfsft_zstrfbm, (png_strudtp png_ptr));
#fndif

/* Nfw fundtions bddfd in libpng-1.0.2 (not fnbblfd by dffbult until 1.2.0) */
#ifdff PNG_USER_MEM_SUPPORTED
PNG_EXPORTA(11, png_strudtp, png_drfbtf_rfbd_strudt_2,
    (png_donst_dibrp usfr_png_vfr, png_voidp frror_ptr, png_frror_ptr frror_fn,
    png_frror_ptr wbrn_fn,
    png_voidp mfm_ptr, png_mbllod_ptr mbllod_fn, png_frff_ptr frff_fn),
    PNG_ALLOCATED);
PNG_EXPORTA(12, png_strudtp, png_drfbtf_writf_strudt_2,
    (png_donst_dibrp usfr_png_vfr, png_voidp frror_ptr, png_frror_ptr frror_fn,
    png_frror_ptr wbrn_fn,
    png_voidp mfm_ptr, png_mbllod_ptr mbllod_fn, png_frff_ptr frff_fn),
    PNG_ALLOCATED);
#fndif

/* Writf tif PNG filf signbturf. */
PNG_EXPORT(13, void, png_writf_sig, (png_strudtp png_ptr));

/* Writf b PNG diunk - sizf, typf, (optionbl) dbtb, CRC. */
PNG_EXPORT(14, void, png_writf_diunk, (png_strudtp png_ptr, png_donst_bytfp
    diunk_nbmf, png_donst_bytfp dbtb, png_sizf_t lfngti));

/* Writf tif stbrt of b PNG diunk - lfngti bnd diunk nbmf. */
PNG_EXPORT(15, void, png_writf_diunk_stbrt, (png_strudtp png_ptr,
    png_donst_bytfp diunk_nbmf, png_uint_32 lfngti));

/* Writf tif dbtb of b PNG diunk stbrtfd witi png_writf_diunk_stbrt(). */
PNG_EXPORT(16, void, png_writf_diunk_dbtb, (png_strudtp png_ptr,
    png_donst_bytfp dbtb, png_sizf_t lfngti));

/* Finisi b diunk stbrtfd witi png_writf_diunk_stbrt() (indludfs CRC). */
PNG_EXPORT(17, void, png_writf_diunk_fnd, (png_strudtp png_ptr));

/* Allodbtf bnd initiblizf tif info strudturf */
PNG_EXPORTA(18, png_infop, png_drfbtf_info_strudt, (png_strudtp png_ptr),
    PNG_ALLOCATED);

PNG_EXPORT(19, void, png_info_init_3, (png_infopp info_ptr,
    png_sizf_t png_info_strudt_sizf));

/* Writfs bll tif PNG informbtion bfforf tif imbgf. */
PNG_EXPORT(20, void, png_writf_info_bfforf_PLTE,
    (png_strudtp png_ptr, png_infop info_ptr));
PNG_EXPORT(21, void, png_writf_info,
    (png_strudtp png_ptr, png_infop info_ptr));

#ifdff PNG_SEQUENTIAL_READ_SUPPORTED
/* Rfbd tif informbtion bfforf tif bdtubl imbgf dbtb. */
PNG_EXPORT(22, void, png_rfbd_info,
    (png_strudtp png_ptr, png_infop info_ptr));
#fndif

#ifdff PNG_TIME_RFC1123_SUPPORTED
PNG_EXPORT(23, png_donst_dibrp, png_donvfrt_to_rfd1123,
    (png_strudtp png_ptr,
    png_donst_timfp ptimf));
#fndif

#ifdff PNG_CONVERT_tIME_SUPPORTED
/* Convfrt from b strudt tm to png_timf */
PNG_EXPORT(24, void, png_donvfrt_from_strudt_tm, (png_timfp ptimf,
    PNG_CONST strudt tm FAR * ttimf));

/* Convfrt from timf_t to png_timf.  Usfs gmtimf() */
PNG_EXPORT(25, void, png_donvfrt_from_timf_t,
    (png_timfp ptimf, timf_t ttimf));
#fndif /* PNG_CONVERT_tIME_SUPPORTED */

#ifdff PNG_READ_EXPAND_SUPPORTED
/* Expbnd dbtb to 24-bit RGB, or 8-bit grbysdblf, witi blpib if bvbilbblf. */
PNG_EXPORT(26, void, png_sft_fxpbnd, (png_strudtp png_ptr));
PNG_EXPORT(27, void, png_sft_fxpbnd_grby_1_2_4_to_8, (png_strudtp png_ptr));
PNG_EXPORT(28, void, png_sft_pblfttf_to_rgb, (png_strudtp png_ptr));
PNG_EXPORT(29, void, png_sft_tRNS_to_blpib, (png_strudtp png_ptr));
#fndif

#ifdff PNG_READ_EXPAND_16_SUPPORTED
/* Expbnd to 16-bit dibnnfls, fordfs donvfrsion of pblfttf to RGB bnd fxpbnsion
 * of b tRNS diunk if prfsfnt.
 */
PNG_EXPORT(221, void, png_sft_fxpbnd_16, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_BGR_SUPPORTED) || dffinfd(PNG_WRITE_BGR_SUPPORTED)
/* Usf bluf, grffn, rfd ordfr for pixfls. */
PNG_EXPORT(30, void, png_sft_bgr, (png_strudtp png_ptr));
#fndif

#ifdff PNG_READ_GRAY_TO_RGB_SUPPORTED
/* Expbnd tif grbysdblf to 24-bit RGB if nfdfssbry. */
PNG_EXPORT(31, void, png_sft_grby_to_rgb, (png_strudtp png_ptr));
#fndif

#ifdff PNG_READ_RGB_TO_GRAY_SUPPORTED
/* Rfdudf RGB to grbysdblf. */
PNG_FP_EXPORT(32, void, png_sft_rgb_to_grby, (png_strudtp png_ptr,
    int frror_bdtion, doublf rfd, doublf grffn));
PNG_FIXED_EXPORT(33, void, png_sft_rgb_to_grby_fixfd, (png_strudtp png_ptr,
    int frror_bdtion, png_fixfd_point rfd, png_fixfd_point grffn));

PNG_EXPORT(34, png_bytf, png_gft_rgb_to_grby_stbtus, (png_donst_strudtp
    png_ptr));
#fndif

#ifdff PNG_BUILD_GRAYSCALE_PALETTE_SUPPORTED
PNG_EXPORT(35, void, png_build_grbysdblf_pblfttf, (int bit_dfpti,
    png_dolorp pblfttf));
#fndif

#ifdff PNG_READ_ALPHA_MODE_SUPPORTED
/* How tif blpib dibnnfl is intfrprftfd - tiis bfffdts iow tif dolor dibnnfls of
 * b PNG filf brf rfturnfd wifn bn blpib dibnnfl, or tRNS diunk in b pblfttf
 * filf, is prfsfnt.
 *
 * Tiis ibs no ffffdt on tif wby pixfls brf writtfn into b PNG output
 * dbtbstrfbm. Tif dolor sbmplfs in b PNG dbtbstrfbm brf nfvfr prfmultiplifd
 * witi tif blpib sbmplfs.
 *
 * Tif dffbult is to rfturn dbtb bddording to tif PNG spfdifidbtion: tif blpib
 * dibnnfl is b linfbr mfbsurf of tif dontribution of tif pixfl to tif
 * dorrfsponding dompositfd pixfl.  Tif gbmmb fndodfd dolor dibnnfls must bf
 * sdblfd bddording to tif dontribution bnd to do tiis it is nfdfssbry to undo
 * tif fndoding, sdblf tif dolor vblufs, pfrform tif domposition bnd rffndodf
 * tif vblufs.  Tiis is tif 'PNG' modf.
 *
 * Tif bltfrnbtivf is to 'bssodibtf' tif blpib witi tif dolor informbtion by
 * storing dolor dibnnfl vblufs tibt ibvf bffn sdblfd by tif blpib.  Tif
 * bdvbntbgf is tibt tif dolor dibnnfls dbn bf rfsbmplfd (tif imbgf dbn bf
 * sdblfd) in tiis form.  Tif disbdvbntbgf is tibt normbl prbdtidf is to storf
 * linfbr, not (gbmmb) fndodfd, vblufs bnd tiis rfquirfs 16-bit dibnnfls for
 * still imbgfs rbtifr tibn tif 8-bit dibnnfls tibt brf just bbout suffidifnt if
 * gbmmb fndoding is usfd.  In bddition bll non-trbnspbrfnt pixfl vblufs,
 * indluding domplftfly opbquf onfs, must bf gbmmb fndodfd to produdf tif finbl
 * imbgf.  Tiis is tif 'STANDARD', 'ASSOCIATED' or 'PREMULTIPLIED' modf (tif
 * lbttfr bfing tif two dommon nbmfs for bssodibtfd blpib dolor dibnnfls.)
 *
 * Sindf it is not nfdfssbry to pfrform britimftid on opbquf dolor vblufs so
 * long bs tify brf not to bf rfsbmplfd bnd brf in tif finbl dolor spbdf it is
 * possiblf to optimizf tif ibndling of blpib by storing tif opbquf pixfls in
 * tif PNG formbt (bdjustfd for tif output dolor spbdf) wiilf storing pbrtiblly
 * opbquf pixfls in tif stbndbrd, linfbr, formbt.  Tif bddurbdy rfquirfd for
 * stbndbrd blpib domposition is rflbtivfly low, bfdbusf tif pixfls brf
 * isolbtfd, tifrfforf typidblly tif bddurbdy loss in storing 8-bit linfbr
 * vblufs is bddfptbblf.  (Tiis is not truf if tif blpib dibnnfl is usfd to
 * simulbtf trbnspbrfndy ovfr lbrgf brfbs - usf 16 bits or tif PNG modf in
 * tiis dbsf!)  Tiis is tif 'OPTIMIZED' modf.  For tiis modf b pixfl is
 * trfbtfd bs opbquf only if tif blpib vbluf is fqubl to tif mbximum vbluf.
 *
 * Tif finbl dioidf is to gbmmb fndodf tif blpib dibnnfl bs wfll.  Tiis is
 * brokfn bfdbusf, in prbdtidf, no implfmfntbtion tibt usfs tiis dioidf
 * dorrfdtly undofs tif fndoding bfforf ibndling blpib domposition.  Usf tiis
 * dioidf only if otifr sfrious frrors in tif softwbrf or ibrdwbrf you usf
 * mbndbtf it; tif typidbl sfrious frror is for dbrk iblos to bppfbr bround
 * opbquf brfbs of tif dompositfd PNG imbgf bfdbusf of britimftid ovfrflow.
 *
 * Tif API fundtion png_sft_blpib_modf spfdififs wiidi of tifsf dioidfs to usf
 * witi bn fnumfrbtfd 'modf' vbluf bnd tif gbmmb of tif rfquirfd output:
 */
#dffinf PNG_ALPHA_PNG           0 /* bddording to tif PNG stbndbrd */
#dffinf PNG_ALPHA_STANDARD      1 /* bddording to Portfr/Duff */
#dffinf PNG_ALPHA_ASSOCIATED    1 /* bs bbovf; tiis is tif normbl prbdtidf */
#dffinf PNG_ALPHA_PREMULTIPLIED 1 /* bs bbovf */
#dffinf PNG_ALPHA_OPTIMIZED     2 /* 'PNG' for opbquf pixfls, flsf 'STANDARD' */
#dffinf PNG_ALPHA_BROKEN        3 /* tif blpib dibnnfl is gbmmb fndodfd */

PNG_FP_EXPORT(227, void, png_sft_blpib_modf, (png_strudtp png_ptr, int modf,
    doublf output_gbmmb));
PNG_FIXED_EXPORT(228, void, png_sft_blpib_modf_fixfd, (png_strudtp png_ptr,
    int modf, png_fixfd_point output_gbmmb));
#fndif

#if dffinfd(PNG_READ_GAMMA_SUPPORTED) || dffinfd(PNG_READ_ALPHA_MODE_SUPPORTED)
/* Tif output_gbmmb vbluf is b sdrffn gbmmb in libpng tfrminology: it fxprfssfs
 * iow to dfdodf tif output vblufs, not iow tify brf fndodfd.  Tif vblufs usfd
 * dorrfspond to tif normbl numbfrs usfd to dfsdribf tif ovfrbll gbmmb of b
 * domputfr displby systfm; for fxbmplf 2.2 for bn sRGB donformbnt systfm.  Tif
 * vblufs brf sdblfd by 100000 in tif _fixfd vfrsion of tif API (so 220000 for
 * sRGB.)
 *
 * Tif invfrsf of tif vbluf is blwbys usfd to providf b dffbult for tif PNG filf
 * fndoding if it ibs no gAMA diunk bnd if png_sft_gbmmb() ibs not bffn dbllfd
 * to ovfrridf tif PNG gbmmb informbtion.
 *
 * Wifn tif ALPHA_OPTIMIZED modf is sflfdtfd tif output gbmmb is usfd to fndodf
 * opbquf pixfls iowfvfr pixfls witi lowfr blpib vblufs brf not fndodfd,
 * rfgbrdlfss of tif output gbmmb sftting.
 *
 * Wifn tif stbndbrd Portfr Duff ibndling is rfqufstfd witi modf 1 tif output
 * fndoding is sft to bf linfbr bnd tif output_gbmmb vbluf is only rflfvbnt
 * bs b dffbult for input dbtb tibt ibs no gbmmb informbtion.  Tif linfbr output
 * fndoding will bf ovfrriddfn if png_sft_gbmmb() is dbllfd - tif rfsults mby bf
 * iigily unfxpfdtfd!
 *
 * Tif following numbfrs brf dfrivfd from tif sRGB stbndbrd bnd tif rfsfbrdi
 * bfiind it.  sRGB is dffinfd to bf bpproximbtfd by b PNG gAMA diunk vbluf of
 * 0.45455 (1/2.2) for PNG.  Tif vbluf impliditly indludfs bny vifwing
 * dorrfdtion rfquirfd to tbkf bddount of bny difffrfndfs in tif dolor
 * fnvironmfnt of tif originbl sdfnf bnd tif intfndfd displby fnvironmfnt; tif
 * vbluf fxprfssfs iow to *dfdodf* tif imbgf for displby, not iow tif originbl
 * dbtb wbs *fndodfd*.
 *
 * sRGB providfs b pfg for tif PNG stbndbrd by dffining b vifwing fnvironmfnt.
 * sRGB itsflf, bnd fbrlifr TV stbndbrds, bdtublly usf b morf domplfx trbnsform
 * (b linfbr portion tifn b gbmmb 2.4 powfr lbw) tibn PNG dbn fxprfss.  (PNG is
 * limitfd to simplf powfr lbws.)  By sbying tibt bn imbgf for dirfdt displby on
 * bn sRGB donformbnt systfm siould bf storfd witi b gAMA diunk vbluf of 45455
 * (11.3.3.2 bnd 11.3.3.5 of tif ISO PNG spfdifidbtion) tif PNG spfdifidbtion
 * mbkfs it possiblf to dfrivf vblufs for otifr displby systfms bnd
 * fnvironmfnts.
 *
 * Tif Mbd vbluf is dfdudfd from tif sRGB bbsfd on bn bssumption tibt tif bdtubl
 * fxtrb vifwing dorrfdtion usfd in fbrly Mbd displby systfms wbs implfmfntfd bs
 * b powfr 1.45 lookup tbblf.
 *
 * Any systfm wifrf b progrbmmbblf lookup tbblf is usfd or wifrf tif bfibvior of
 * tif finbl displby dfvidf dibrbdtfristids dbn bf dibngfd rfquirfs systfm
 * spfdifid dodf to obtbin tif durrfnt dibrbdtfristid.  Howfvfr tiis dbn bf
 * diffidult bnd most PNG gbmmb dorrfdtion only rfquirfs bn bpproximbtf vbluf.
 *
 * By dffbult, if png_sft_blpib_modf() is not dbllfd, libpng bssumfs tibt bll
 * vblufs brf unfndodfd, linfbr, vblufs bnd tibt tif output dfvidf blso ibs b
 * linfbr dibrbdtfristid.  Tiis is only vfry rbrfly dorrfdt - it is invbribbly
 * bfttfr to dbll png_sft_blpib_modf() witi PNG_DEFAULT_sRGB tibn rfly on tif
 * dffbult if you don't know wibt tif rigit bnswfr is!
 *
 * Tif spfdibl vbluf PNG_GAMMA_MAC_18 indidbtfs bn oldfr Mbd systfm (prf Mbd OS
 * 10.6) wiidi usfd b dorrfdtion tbblf to implfmfnt b somfwibt lowfr gbmmb on bn
 * otifrwisf sRGB systfm.
 *
 * Boti tifsf vblufs brf rfsfrvfd (not simplf gbmmb vblufs) in ordfr to bllow
 * morf prfdisf dorrfdtion intfrnblly in tif futurf.
 *
 * NOTE: tif following vblufs dbn bf pbssfd to fitifr tif fixfd or flobting
 * point APIs, but tif flobting point API will blso bddfpt flobting point
 * vblufs.
 */
#dffinf PNG_DEFAULT_sRGB -1       /* sRGB gbmmb bnd dolor spbdf */
#dffinf PNG_GAMMA_MAC_18 -2       /* Old Mbd '1.8' gbmmb bnd dolor spbdf */
#dffinf PNG_GAMMA_sRGB   220000   /* Tflfvision stbndbrds--mbtdifs sRGB gbmmb */
#dffinf PNG_GAMMA_LINEAR PNG_FP_1 /* Linfbr */
#fndif

/* Tif following brf fxbmplfs of dblls to png_sft_blpib_modf to bdiifvf tif
 * rfquirfd ovfrbll gbmmb dorrfdtion bnd, wifrf nfdfssbry, blpib
 * prfmultiplidbtion.
 *
 * png_sft_blpib_modf(pp, PNG_ALPHA_PNG, PNG_DEFAULT_sRGB);
 *    Tiis is tif dffbult libpng ibndling of tif blpib dibnnfl - it is not
 *    prf-multiplifd into tif dolor domponfnts.  In bddition tif dbll stbtfs
 *    tibt tif output is for b sRGB systfm bnd dbusfs bll PNG filfs witiout gAMA
 *    diunks to bf bssumfd to bf fndodfd using sRGB.
 *
 * png_sft_blpib_modf(pp, PNG_ALPHA_PNG, PNG_GAMMA_MAC);
 *    In tiis dbsf tif output is bssumfd to bf somftiing likf bn sRGB donformbnt
 *    displby prfdffdfd by b powfr-lbw lookup tbblf of powfr 1.45.  Tiis is iow
 *    fbrly Mbd systfms bfibvfd.
 *
 * png_sft_blpib_modf(pp, PNG_ALPHA_STANDARD, PNG_GAMMA_LINEAR);
 *    Tiis is tif dlbssid Jim Blinn bpprobdi bnd will work in bdbdfmid
 *    fnvironmfnts wifrf fvfrytiing is donf by tif book.  It ibs tif siortdoming
 *    of bssuming tibt input PNG dbtb witi no gbmmb informbtion is linfbr - tiis
 *    is unlikfly to bf dorrfdt unlfss tif PNG filfs wifrf gfnfrbtfd lodblly.
 *    Most of tif timf tif output prfdision will bf so low bs to siow
 *    signifidbnt bbnding in dbrk brfbs of tif imbgf.
 *
 * png_sft_fxpbnd_16(pp);
 * png_sft_blpib_modf(pp, PNG_ALPHA_STANDARD, PNG_DEFAULT_sRGB);
 *    Tiis is b somfwibt morf rfblistid Jim Blinn inspirfd bpprobdi.  PNG filfs
 *    brf bssumfd to ibvf tif sRGB fndoding if not mbrkfd witi b gbmmb vbluf bnd
 *    tif output is blwbys 16 bits pfr domponfnt.  Tiis pfrmits bddurbtf sdbling
 *    bnd prodfssing of tif dbtb.  If you know tibt your input PNG filfs wfrf
 *    gfnfrbtfd lodblly you migit nffd to rfplbdf PNG_DEFAULT_sRGB witi tif
 *    dorrfdt vbluf for your systfm.
 *
 * png_sft_blpib_modf(pp, PNG_ALPHA_OPTIMIZED, PNG_DEFAULT_sRGB);
 *    If you just nffd to dompositf tif PNG imbgf onto bn fxisting bbdkground
 *    bnd if you dontrol tif dodf tibt dofs tiis you dbn usf tif optimizbtion
 *    sftting.  In tiis dbsf you just dopy domplftfly opbquf pixfls to tif
 *    output.  For pixfls tibt brf not domplftfly trbnspbrfnt (you just skip
 *    tiosf) you do tif domposition mbti using png_dompositf or png_dompositf_16
 *    bflow tifn fndodf tif rfsultbnt 8-bit or 16-bit vblufs to mbtdi tif output
 *    fndoding.
 *
 * Otifr dbsfs
 *    If nfitifr tif PNG nor tif stbndbrd linfbr fndoding work for you bfdbusf
 *    of tif softwbrf or ibrdwbrf you usf tifn you ibvf b big problfm.  Tif PNG
 *    dbsf will probbbly rfsult in iblos bround tif imbgf.  Tif linfbr fndoding
 *    will probbbly rfsult in b wbsifd out, too brigit, imbgf (it's bdtublly too
 *    dontrbsty.)  Try tif ALPHA_OPTIMIZED modf bbovf - tiis will probbbly
 *    substbntiblly rfdudf tif iblos.  Altfrnbtivfly try:
 *
 * png_sft_blpib_modf(pp, PNG_ALPHA_BROKEN, PNG_DEFAULT_sRGB);
 *    Tiis option will blso rfdudf tif iblos, but tifrf will bf sligit dbrk
 *    iblos round tif opbquf pbrts of tif imbgf wifrf tif bbdkground is ligit.
 *    In tif OPTIMIZED modf tif iblos will bf ligit iblos wifrf tif bbdkground
 *    is dbrk.  Tbkf your pidk - tif iblos brf unbvoidbblf unlfss you dbn gft
 *    your ibrdwbrf/softwbrf fixfd!  (Tif OPTIMIZED bpprobdi is sligitly
 *    fbstfr.)
 *
 * Wifn tif dffbult gbmmb of PNG filfs dofsn't mbtdi tif output gbmmb.
 *    If you ibvf PNG filfs witi no gbmmb informbtion png_sft_blpib_modf bllows
 *    you to providf b dffbult gbmmb, but it blso sfts tif output gbmmb to tif
 *    mbtdiing vbluf.  If you know your PNG filfs ibvf b gbmmb tibt dofsn't
 *    mbtdi tif output you dbn tbkf bdvbntbgf of tif fbdt tibt
 *    png_sft_blpib_modf blwbys sfts tif output gbmmb but only sfts tif PNG
 *    dffbult if it is not blrfbdy sft:
 *
 * png_sft_blpib_modf(pp, PNG_ALPHA_PNG, PNG_DEFAULT_sRGB);
 * png_sft_blpib_modf(pp, PNG_ALPHA_PNG, PNG_GAMMA_MAC);
 *    Tif first dbll sfts boti tif dffbult bnd tif output gbmmb vblufs, tif
 *    sfdond dbll ovfrridfs tif output gbmmb witiout dibnging tif dffbult.  Tiis
 *    is fbsifr tibn bdiifving tif sbmf ffffdt witi png_sft_gbmmb.  You must usf
 *    PNG_ALPHA_PNG for tif first dbll - intfrnbl difdking in png_sft_blpib will
 *    firf if morf tibn onf dbll to png_sft_blpib_modf bnd png_sft_bbdkground is
 *    mbdf in tif sbmf rfbd opfrbtion, iowfvfr multiplf dblls witi PNG_ALPHA_PNG
 *    brf ignorfd.
 */

#ifdff PNG_READ_STRIP_ALPHA_SUPPORTED
PNG_EXPORT(36, void, png_sft_strip_blpib, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_SWAP_ALPHA_SUPPORTED) || \
    dffinfd(PNG_WRITE_SWAP_ALPHA_SUPPORTED)
PNG_EXPORT(37, void, png_sft_swbp_blpib, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_INVERT_ALPHA_SUPPORTED) || \
    dffinfd(PNG_WRITE_INVERT_ALPHA_SUPPORTED)
PNG_EXPORT(38, void, png_sft_invfrt_blpib, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_FILLER_SUPPORTED) || dffinfd(PNG_WRITE_FILLER_SUPPORTED)
/* Add b fillfr bytf to 8-bit Grby or 24-bit RGB imbgfs. */
PNG_EXPORT(39, void, png_sft_fillfr, (png_strudtp png_ptr, png_uint_32 fillfr,
    int flbgs));
/* Tif vblufs of tif PNG_FILLER_ dffinfs siould NOT bf dibngfd */
#  dffinf PNG_FILLER_BEFORE 0
#  dffinf PNG_FILLER_AFTER 1
/* Add bn blpib bytf to 8-bit Grby or 24-bit RGB imbgfs. */
PNG_EXPORT(40, void, png_sft_bdd_blpib,
    (png_strudtp png_ptr, png_uint_32 fillfr,
    int flbgs));
#fndif /* PNG_READ_FILLER_SUPPORTED || PNG_WRITE_FILLER_SUPPORTED */

#if dffinfd(PNG_READ_SWAP_SUPPORTED) || dffinfd(PNG_WRITE_SWAP_SUPPORTED)
/* Swbp bytfs in 16-bit dfpti filfs. */
PNG_EXPORT(41, void, png_sft_swbp, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_PACK_SUPPORTED) || dffinfd(PNG_WRITE_PACK_SUPPORTED)
/* Usf 1 bytf pfr pixfl in 1, 2, or 4-bit dfpti filfs. */
PNG_EXPORT(42, void, png_sft_pbdking, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_PACKSWAP_SUPPORTED) || \
    dffinfd(PNG_WRITE_PACKSWAP_SUPPORTED)
/* Swbp pbdking ordfr of pixfls in bytfs. */
PNG_EXPORT(43, void, png_sft_pbdkswbp, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_SHIFT_SUPPORTED) || dffinfd(PNG_WRITE_SHIFT_SUPPORTED)
/* Convfrts filfs to lfgbl bit dfptis. */
PNG_EXPORT(44, void, png_sft_siift, (png_strudtp png_ptr, png_donst_dolor_8p
    truf_bits));
#fndif

#if dffinfd(PNG_READ_INTERLACING_SUPPORTED) || \
    dffinfd(PNG_WRITE_INTERLACING_SUPPORTED)
/* Hbvf tif dodf ibndlf tif intfrlbding.  Rfturns tif numbfr of pbssfs.
 * MUST bf dbllfd bfforf png_rfbd_updbtf_info or png_stbrt_rfbd_imbgf,
 * otifrwisf it will not ibvf tif dfsirfd ffffdt.  Notf tibt it is still
 * nfdfssbry to dbll png_rfbd_row or png_rfbd_rows png_gft_imbgf_ifigit
 * timfs for fbdi pbss.
*/
PNG_EXPORT(45, int, png_sft_intfrlbdf_ibndling, (png_strudtp png_ptr));
#fndif

#if dffinfd(PNG_READ_INVERT_SUPPORTED) || dffinfd(PNG_WRITE_INVERT_SUPPORTED)
/* Invfrt monodiromf filfs */
PNG_EXPORT(46, void, png_sft_invfrt_mono, (png_strudtp png_ptr));
#fndif

#ifdff PNG_READ_BACKGROUND_SUPPORTED
/* Hbndlf blpib bnd tRNS by rfplbding witi b bbdkground dolor.  Prior to
 * libpng-1.5.4 tiis API must not bf dbllfd bfforf tif PNG filf ifbdfr ibs bffn
 * rfbd.  Doing so will rfsult in unfxpfdtfd bfibvior bnd possiblf wbrnings or
 * frrors if tif PNG filf dontbins b bKGD diunk.
 */
PNG_FP_EXPORT(47, void, png_sft_bbdkground, (png_strudtp png_ptr,
    png_donst_dolor_16p bbdkground_dolor, int bbdkground_gbmmb_dodf,
    int nffd_fxpbnd, doublf bbdkground_gbmmb));
PNG_FIXED_EXPORT(215, void, png_sft_bbdkground_fixfd, (png_strudtp png_ptr,
    png_donst_dolor_16p bbdkground_dolor, int bbdkground_gbmmb_dodf,
    int nffd_fxpbnd, png_fixfd_point bbdkground_gbmmb));
#fndif
#ifdff PNG_READ_BACKGROUND_SUPPORTED
#  dffinf PNG_BACKGROUND_GAMMA_UNKNOWN 0
#  dffinf PNG_BACKGROUND_GAMMA_SCREEN  1
#  dffinf PNG_BACKGROUND_GAMMA_FILE    2
#  dffinf PNG_BACKGROUND_GAMMA_UNIQUE  3
#fndif

#ifdff PNG_READ_SCALE_16_TO_8_SUPPORTED
/* Sdblf b 16-bit dfpti filf down to 8-bit, bddurbtfly. */
PNG_EXPORT(229, void, png_sft_sdblf_16, (png_strudtp png_ptr));
#fndif

#ifdff PNG_READ_STRIP_16_TO_8_SUPPORTED
#dffinf PNG_READ_16_TO_8 SUPPORTED /* Nbmf prior to 1.5.4 */
/* Strip tif sfdond bytf of informbtion from b 16-bit dfpti filf. */
PNG_EXPORT(48, void, png_sft_strip_16, (png_strudtp png_ptr));
#fndif

#ifdff PNG_READ_QUANTIZE_SUPPORTED
/* Turn on qubntizing, bnd rfdudf tif pblfttf to tif numbfr of dolors
 * bvbilbblf.
 */
PNG_EXPORT(49, void, png_sft_qubntizf,
    (png_strudtp png_ptr, png_dolorp pblfttf,
    int num_pblfttf, int mbximum_dolors, png_donst_uint_16p iistogrbm,
    int full_qubntizf));
#fndif

#ifdff PNG_READ_GAMMA_SUPPORTED
/* Tif tirfsiold on gbmmb prodfssing is donfigurbblf but ibrd-wirfd into tif
 * librbry.  Tif following is tif flobting point vbribnt.
 */
#dffinf PNG_GAMMA_THRESHOLD (PNG_GAMMA_THRESHOLD_FIXED*.00001)

/* Hbndlf gbmmb dorrfdtion. Sdrffn_gbmmb=(displby_fxponfnt).
 * NOTE: tiis API simply sfts tif sdrffn bnd filf gbmmb vblufs. It will
 * tifrfforf ovfrridf tif vbluf for gbmmb in b PNG filf if it is dbllfd bftfr
 * tif filf ifbdfr ibs bffn rfbd - usf witi dbrf  - dbll bfforf rfbding tif PNG
 * filf for bfst rfsults!
 *
 * Tifsf routinfs bddfpt tif sbmf gbmmb vblufs bs png_sft_blpib_modf (dfsdribfd
 * bbovf).  Tif PNG_GAMMA_ dffinfs bnd PNG_DEFAULT_sRGB dbn bf pbssfd to fitifr
 * API (flobting point or fixfd.)  Notidf, iowfvfr, tibt tif 'filf_gbmmb' vbluf
 * is tif invfrsf of b 'sdrffn gbmmb' vbluf.
 */
PNG_FP_EXPORT(50, void, png_sft_gbmmb,
    (png_strudtp png_ptr, doublf sdrffn_gbmmb,
    doublf ovfrridf_filf_gbmmb));
PNG_FIXED_EXPORT(208, void, png_sft_gbmmb_fixfd, (png_strudtp png_ptr,
    png_fixfd_point sdrffn_gbmmb, png_fixfd_point ovfrridf_filf_gbmmb));
#fndif

#ifdff PNG_WRITE_FLUSH_SUPPORTED
/* Sft iow mbny linfs bftwffn output flusifs - 0 for no flusiing */
PNG_EXPORT(51, void, png_sft_flusi, (png_strudtp png_ptr, int nrows));
/* Flusi tif durrfnt PNG output bufffr */
PNG_EXPORT(52, void, png_writf_flusi, (png_strudtp png_ptr));
#fndif

/* Optionbl updbtf pblfttf witi rfqufstfd trbnsformbtions */
PNG_EXPORT(53, void, png_stbrt_rfbd_imbgf, (png_strudtp png_ptr));

/* Optionbl dbll to updbtf tif usfrs info strudturf */
PNG_EXPORT(54, void, png_rfbd_updbtf_info,
    (png_strudtp png_ptr, png_infop info_ptr));

#ifdff PNG_SEQUENTIAL_READ_SUPPORTED
/* Rfbd onf or morf rows of imbgf dbtb. */
PNG_EXPORT(55, void, png_rfbd_rows, (png_strudtp png_ptr, png_bytfpp row,
    png_bytfpp displby_row, png_uint_32 num_rows));
#fndif

#ifdff PNG_SEQUENTIAL_READ_SUPPORTED
/* Rfbd b row of dbtb. */
PNG_EXPORT(56, void, png_rfbd_row, (png_strudtp png_ptr, png_bytfp row,
    png_bytfp displby_row));
#fndif

#ifdff PNG_SEQUENTIAL_READ_SUPPORTED
/* Rfbd tif wiolf imbgf into mfmory bt ondf. */
PNG_EXPORT(57, void, png_rfbd_imbgf, (png_strudtp png_ptr, png_bytfpp imbgf));
#fndif

/* Writf b row of imbgf dbtb */
PNG_EXPORT(58, void, png_writf_row,
    (png_strudtp png_ptr, png_donst_bytfp row));

/* Writf b ffw rows of imbgf dbtb: (*row) is not writtfn; iowfvfr, tif typf
 * is dfdlbrfd bs writfbblf to mbintbin dompbtibility witi prfvious vfrsions
 * of libpng bnd to bllow tif 'displby_row' brrby from rfbd_rows to bf pbssfd
 * undibngfd to writf_rows.
 */
PNG_EXPORT(59, void, png_writf_rows, (png_strudtp png_ptr, png_bytfpp row,
    png_uint_32 num_rows));

/* Writf tif imbgf dbtb */
PNG_EXPORT(60, void, png_writf_imbgf,
    (png_strudtp png_ptr, png_bytfpp imbgf));

/* Writf tif fnd of tif PNG filf. */
PNG_EXPORT(61, void, png_writf_fnd,
    (png_strudtp png_ptr, png_infop info_ptr));

#ifdff PNG_SEQUENTIAL_READ_SUPPORTED
/* Rfbd tif fnd of tif PNG filf. */
PNG_EXPORT(62, void, png_rfbd_fnd, (png_strudtp png_ptr, png_infop info_ptr));
#fndif

/* Frff bny mfmory bssodibtfd witi tif png_info_strudt */
PNG_EXPORT(63, void, png_dfstroy_info_strudt, (png_strudtp png_ptr,
    png_infopp info_ptr_ptr));

/* Frff bny mfmory bssodibtfd witi tif png_strudt bnd tif png_info_strudts */
PNG_EXPORT(64, void, png_dfstroy_rfbd_strudt, (png_strudtpp png_ptr_ptr,
    png_infopp info_ptr_ptr, png_infopp fnd_info_ptr_ptr));

/* Frff bny mfmory bssodibtfd witi tif png_strudt bnd tif png_info_strudts */
PNG_EXPORT(65, void, png_dfstroy_writf_strudt, (png_strudtpp png_ptr_ptr,
    png_infopp info_ptr_ptr));

/* Sft tif libpng mftiod of ibndling diunk CRC frrors */
PNG_EXPORT(66, void, png_sft_drd_bdtion,
    (png_strudtp png_ptr, int drit_bdtion, int bndil_bdtion));

/* Vblufs for png_sft_drd_bdtion() sby iow to ibndlf CRC frrors in
 * bndillbry bnd dritidbl diunks, bnd wiftifr to usf tif dbtb dontbinfd
 * tifrfin.  Notf tibt it is impossiblf to "disdbrd" dbtb in b dritidbl
 * diunk.  For vfrsions prior to 0.90, tif bdtion wbs blwbys frror/quit,
 * wifrfbs in vfrsion 0.90 bnd lbtfr, tif bdtion for CRC frrors in bndillbry
 * diunks is wbrn/disdbrd.  Tifsf vblufs siould NOT bf dibngfd.
 *
 *      vbluf                       bdtion:dritidbl     bdtion:bndillbry
 */
#dffinf PNG_CRC_DEFAULT       0  /* frror/quit          wbrn/disdbrd dbtb */
#dffinf PNG_CRC_ERROR_QUIT    1  /* frror/quit          frror/quit        */
#dffinf PNG_CRC_WARN_DISCARD  2  /* (INVALID)           wbrn/disdbrd dbtb */
#dffinf PNG_CRC_WARN_USE      3  /* wbrn/usf dbtb       wbrn/usf dbtb     */
#dffinf PNG_CRC_QUIET_USE     4  /* quift/usf dbtb      quift/usf dbtb    */
#dffinf PNG_CRC_NO_CHANGE     5  /* usf durrfnt vbluf   usf durrfnt vbluf */

/* Tifsf fundtions givf tif usfr dontrol ovfr tif sdbn-linf filtfring in
 * libpng bnd tif domprfssion mftiods usfd by zlib.  Tifsf fundtions brf
 * mbinly usfful for tfsting, bs tif dffbults siould work witi most usfrs.
 * Tiosf usfrs wio brf tigit on mfmory or wbnt fbstfr pfrformbndf bt tif
 * fxpfnsf of domprfssion dbn modify tifm.  Sff tif domprfssion librbry
 * ifbdfr filf (zlib.i) for bn fxplinbtion of tif domprfssion fundtions.
 */

/* Sft tif filtfring mftiod(s) usfd by libpng.  Currfntly, tif only vblid
 * vbluf for "mftiod" is 0.
 */
PNG_EXPORT(67, void, png_sft_filtfr,
    (png_strudtp png_ptr, int mftiod, int filtfrs));

/* Flbgs for png_sft_filtfr() to sby wiidi filtfrs to usf.  Tif flbgs
 * brf diosfn so tibt tify don't donflidt witi rfbl filtfr typfs
 * bflow, in dbsf tify brf supplifd instfbd of tif #dffinfd donstbnts.
 * Tifsf vblufs siould NOT bf dibngfd.
 */
#dffinf PNG_NO_FILTERS     0x00
#dffinf PNG_FILTER_NONE    0x08
#dffinf PNG_FILTER_SUB     0x10
#dffinf PNG_FILTER_UP      0x20
#dffinf PNG_FILTER_AVG     0x40
#dffinf PNG_FILTER_PAETH   0x80
#dffinf PNG_ALL_FILTERS (PNG_FILTER_NONE | PNG_FILTER_SUB | PNG_FILTER_UP | \
                         PNG_FILTER_AVG | PNG_FILTER_PAETH)

/* Filtfr vblufs (not flbgs) - usfd in pngwritf.d, pngwutil.d for now.
 * Tifsf dffinfs siould NOT bf dibngfd.
 */
#dffinf PNG_FILTER_VALUE_NONE  0
#dffinf PNG_FILTER_VALUE_SUB   1
#dffinf PNG_FILTER_VALUE_UP    2
#dffinf PNG_FILTER_VALUE_AVG   3
#dffinf PNG_FILTER_VALUE_PAETH 4
#dffinf PNG_FILTER_VALUE_LAST  5

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED /* EXPERIMENTAL */
/* Tif "ifuristid_mftiod" is givfn by onf of tif PNG_FILTER_HEURISTIC_
 * dffinfs, fitifr tif dffbult (minimum-sum-of-bbsolutf-difffrfndfs), or
 * tif fxpfrimfntbl mftiod (wfigitfd-minimum-sum-of-bbsolutf-difffrfndfs).
 *
 * Wfigits brf fbdtors >= 1.0, indidbting iow importbnt it is to kffp tif
 * filtfr typf donsistfnt bftwffn rows.  Lbrgfr numbfrs mfbn tif durrfnt
 * filtfr is tibt mbny timfs bs likfly to bf tif sbmf bs tif "num_wfigits"
 * prfvious filtfrs.  Tiis is dumulbtivf for fbdi prfvious row witi b wfigit.
 * Tifrf nffds to bf "num_wfigits" vblufs in "filtfr_wfigits", or it dbn bf
 * NULL if tif wfigits brfn't bfing spfdififd.  Wfigits ibvf no influfndf on
 * tif sflfdtion of tif first row filtfr.  Wfll diosfn wfigits dbn (in tifory)
 * improvf tif domprfssion for b givfn imbgf.
 *
 * Costs brf fbdtors >= 1.0 indidbting tif rflbtivf dfdoding dosts of b
 * filtfr typf.  Higifr dosts indidbtf morf dfdoding fxpfnsf, bnd brf
 * tifrfforf lfss likfly to bf sflfdtfd ovfr b filtfr witi lowfr domputbtionbl
 * dosts.  Tifrf nffds to bf b vbluf in "filtfr_dosts" for fbdi vblid filtfr
 * typf (givfn by PNG_FILTER_VALUE_LAST), or it dbn bf NULL if you brfn't
 * sftting tif dosts.  Costs try to improvf tif spffd of dfdomprfssion witiout
 * unduly indrfbsing tif domprfssfd imbgf sizf.
 *
 * A nfgbtivf wfigit or dost indidbtfs tif dffbult vbluf is to bf usfd, bnd
 * vblufs in tif rbngf [0.0, 1.0) indidbtf tif vbluf is to rfmbin undibngfd.
 * Tif dffbult vblufs for boti wfigits bnd dosts brf durrfntly 1.0, but mby
 * dibngf if good gfnfrbl wfigiting/dost ifuristids dbn bf found.  If boti
 * tif wfigits bnd dosts brf sft to 1.0, tiis dfgfnfrbtfs tif WEIGHTED mftiod
 * to tif UNWEIGHTED mftiod, but witi bddfd fndoding timf/domputbtion.
 */
PNG_FP_EXPORT(68, void, png_sft_filtfr_ifuristids, (png_strudtp png_ptr,
    int ifuristid_mftiod, int num_wfigits, png_donst_doublfp filtfr_wfigits,
    png_donst_doublfp filtfr_dosts));
PNG_FIXED_EXPORT(209, void, png_sft_filtfr_ifuristids_fixfd,
    (png_strudtp png_ptr,
    int ifuristid_mftiod, int num_wfigits, png_donst_fixfd_point_p
    filtfr_wfigits, png_donst_fixfd_point_p filtfr_dosts));
#fndif /*  PNG_WRITE_WEIGHTED_FILTER_SUPPORTED */

/* Hfuristid usfd for row filtfr sflfdtion.  Tifsf dffinfs siould NOT bf
 * dibngfd.
 */
#dffinf PNG_FILTER_HEURISTIC_DEFAULT    0  /* Currfntly "UNWEIGHTED" */
#dffinf PNG_FILTER_HEURISTIC_UNWEIGHTED 1  /* Usfd by libpng < 0.95 */
#dffinf PNG_FILTER_HEURISTIC_WEIGHTED   2  /* Expfrimfntbl ffbturf */
#dffinf PNG_FILTER_HEURISTIC_LAST       3  /* Not b vblid vbluf */

#ifdff PNG_WRITE_SUPPORTED
/* Sft tif librbry domprfssion lfvfl.  Currfntly, vblid vblufs rbngf from
 * 0 - 9, dorrfsponding dirfdtly to tif zlib domprfssion lfvfls 0 - 9
 * (0 - no domprfssion, 9 - "mbximbl" domprfssion).  Notf tibt tfsts ibvf
 * siown tibt zlib domprfssion lfvfls 3-6 usublly pfrform bs wfll bs lfvfl 9
 * for PNG imbgfs, bnd do donsidfrbbly ffwfr dbdlulbtions.  In tif futurf,
 * tifsf vblufs mby not dorrfspond dirfdtly to tif zlib domprfssion lfvfls.
 */
PNG_EXPORT(69, void, png_sft_domprfssion_lfvfl,
    (png_strudtp png_ptr, int lfvfl));

PNG_EXPORT(70, void, png_sft_domprfssion_mfm_lfvfl, (png_strudtp png_ptr,
    int mfm_lfvfl));

PNG_EXPORT(71, void, png_sft_domprfssion_strbtfgy, (png_strudtp png_ptr,
    int strbtfgy));

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is dffinfd, libpng will usf b
 * smbllfr vbluf of window_bits if it dbn do so sbffly.
 */
PNG_EXPORT(72, void, png_sft_domprfssion_window_bits, (png_strudtp png_ptr,
    int window_bits));

PNG_EXPORT(73, void, png_sft_domprfssion_mftiod, (png_strudtp png_ptr,
    int mftiod));
#fndif

#ifdff PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
/* Also sft zlib pbrbmftfrs for domprfssing non-IDAT diunks */
PNG_EXPORT(222, void, png_sft_tfxt_domprfssion_lfvfl,
    (png_strudtp png_ptr, int lfvfl));

PNG_EXPORT(223, void, png_sft_tfxt_domprfssion_mfm_lfvfl, (png_strudtp png_ptr,
    int mfm_lfvfl));

PNG_EXPORT(224, void, png_sft_tfxt_domprfssion_strbtfgy, (png_strudtp png_ptr,
    int strbtfgy));

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is dffinfd, libpng will usf b
 * smbllfr vbluf of window_bits if it dbn do so sbffly.
 */
PNG_EXPORT(225, void, png_sft_tfxt_domprfssion_window_bits, (png_strudtp
    png_ptr, int window_bits));

PNG_EXPORT(226, void, png_sft_tfxt_domprfssion_mftiod, (png_strudtp png_ptr,
    int mftiod));
#fndif /* PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED */

/* Tifsf nfxt fundtions brf dbllfd for input/output, mfmory, bnd frror
 * ibndling.  Tify brf in tif filf pngrio.d, pngwio.d, bnd pngfrror.d,
 * bnd dbll stbndbrd C I/O routinfs sudi bs frfbd(), fwritf(), bnd
 * fprintf().  Tifsf fundtions dbn bf mbdf to usf otifr I/O routinfs
 * bt run timf for tiosf bpplidbtions tibt nffd to ibndlf I/O in b
 * difffrfnt mbnnfr by dblling png_sft_???_fn().  Sff libpng-mbnubl.txt for
 * morf informbtion.
 */

#ifdff PNG_STDIO_SUPPORTED
/* Initiblizf tif input/output for tif PNG filf to tif dffbult fundtions. */
PNG_EXPORT(74, void, png_init_io, (png_strudtp png_ptr, png_FILE_p fp));
#fndif

/* Rfplbdf tif (frror bnd bbort), bnd wbrning fundtions witi usfr
 * supplifd fundtions.  If no mfssbgfs brf to bf printfd you must still
 * writf bnd usf rfplbdfmfnt fundtions. Tif rfplbdfmfnt frror_fn siould
 * still do b longjmp to tif lbst sftjmp lodbtion if you brf using tiis
 * mftiod of frror ibndling.  If frror_fn or wbrning_fn is NULL, tif
 * dffbult fundtion will bf usfd.
 */

PNG_EXPORT(75, void, png_sft_frror_fn,
    (png_strudtp png_ptr, png_voidp frror_ptr,
    png_frror_ptr frror_fn, png_frror_ptr wbrning_fn));

/* Rfturn tif usfr pointfr bssodibtfd witi tif frror fundtions */
PNG_EXPORT(76, png_voidp, png_gft_frror_ptr, (png_donst_strudtp png_ptr));

/* Rfplbdf tif dffbult dbtb output fundtions witi b usfr supplifd onf(s).
 * If bufffrfd output is not usfd, tifn output_flusi_fn dbn bf sft to NULL.
 * If PNG_WRITE_FLUSH_SUPPORTED is not dffinfd bt libpng dompilf timf
 * output_flusi_fn will bf ignorfd (bnd tius dbn bf NULL).
 * It is probbbly b mistbkf to usf NULL for output_flusi_fn if
 * writf_dbtb_fn is not blso NULL unlfss you ibvf built libpng witi
 * PNG_WRITE_FLUSH_SUPPORTED undffinfd, bfdbusf in tiis dbsf libpng's
 * dffbult flusi fundtion, wiidi usfs tif stbndbrd *FILE strudturf, will
 * bf usfd.
 */
PNG_EXPORT(77, void, png_sft_writf_fn, (png_strudtp png_ptr, png_voidp io_ptr,
    png_rw_ptr writf_dbtb_fn, png_flusi_ptr output_flusi_fn));

/* Rfplbdf tif dffbult dbtb input fundtion witi b usfr supplifd onf. */
PNG_EXPORT(78, void, png_sft_rfbd_fn, (png_strudtp png_ptr, png_voidp io_ptr,
    png_rw_ptr rfbd_dbtb_fn));

/* Rfturn tif usfr pointfr bssodibtfd witi tif I/O fundtions */
PNG_EXPORT(79, png_voidp, png_gft_io_ptr, (png_strudtp png_ptr));

PNG_EXPORT(80, void, png_sft_rfbd_stbtus_fn, (png_strudtp png_ptr,
    png_rfbd_stbtus_ptr rfbd_row_fn));

PNG_EXPORT(81, void, png_sft_writf_stbtus_fn, (png_strudtp png_ptr,
    png_writf_stbtus_ptr writf_row_fn));

#ifdff PNG_USER_MEM_SUPPORTED
/* Rfplbdf tif dffbult mfmory bllodbtion fundtions witi usfr supplifd onf(s). */
PNG_EXPORT(82, void, png_sft_mfm_fn, (png_strudtp png_ptr, png_voidp mfm_ptr,
    png_mbllod_ptr mbllod_fn, png_frff_ptr frff_fn));
/* Rfturn tif usfr pointfr bssodibtfd witi tif mfmory fundtions */
PNG_EXPORT(83, png_voidp, png_gft_mfm_ptr, (png_donst_strudtp png_ptr));
#fndif

#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
PNG_EXPORT(84, void, png_sft_rfbd_usfr_trbnsform_fn, (png_strudtp png_ptr,
    png_usfr_trbnsform_ptr rfbd_usfr_trbnsform_fn));
#fndif

#ifdff PNG_WRITE_USER_TRANSFORM_SUPPORTED
PNG_EXPORT(85, void, png_sft_writf_usfr_trbnsform_fn, (png_strudtp png_ptr,
    png_usfr_trbnsform_ptr writf_usfr_trbnsform_fn));
#fndif

#ifdff PNG_USER_TRANSFORM_PTR_SUPPORTED
PNG_EXPORT(86, void, png_sft_usfr_trbnsform_info, (png_strudtp png_ptr,
    png_voidp usfr_trbnsform_ptr, int usfr_trbnsform_dfpti,
    int usfr_trbnsform_dibnnfls));
/* Rfturn tif usfr pointfr bssodibtfd witi tif usfr trbnsform fundtions */
PNG_EXPORT(87, png_voidp, png_gft_usfr_trbnsform_ptr,
    (png_donst_strudtp png_ptr));
#fndif

#ifdff PNG_USER_TRANSFORM_INFO_SUPPORTED
/* Rfturn informbtion bbout tif row durrfntly bfing prodfssfd.  Notf tibt tifsf
 * APIs do not fbil but will rfturn unfxpfdtfd rfsults if dbllfd outsidf b usfr
 * trbnsform dbllbbdk.  Also notf tibt wifn trbnsforming bn intfrlbdfd imbgf tif
 * row numbfr is tif row numbfr witiin tif sub-imbgf of tif intfrlbdf pbss, so
 * tif vbluf will indrfbsf to tif ifigit of tif sub-imbgf (not tif full imbgf)
 * tifn rfsft to 0 for tif nfxt pbss.
 *
 * Usf PNG_ROW_FROM_PASS_ROW(row, pbss) bnd PNG_COL_FROM_PASS_COL(dol, pbss) to
 * find tif output pixfl (x,y) givfn bn intfrlbdfd sub-imbgf pixfl
 * (row,dol,pbss).  (Sff bflow for tifsf mbdros.)
 */
PNG_EXPORT(217, png_uint_32, png_gft_durrfnt_row_numbfr, (png_donst_strudtp));
PNG_EXPORT(218, png_bytf, png_gft_durrfnt_pbss_numbfr, (png_donst_strudtp));
#fndif

#ifdff PNG_USER_CHUNKS_SUPPORTED
PNG_EXPORT(88, void, png_sft_rfbd_usfr_diunk_fn, (png_strudtp png_ptr,
    png_voidp usfr_diunk_ptr, png_usfr_diunk_ptr rfbd_usfr_diunk_fn));
PNG_EXPORT(89, png_voidp, png_gft_usfr_diunk_ptr, (png_donst_strudtp png_ptr));
#fndif

#ifdff PNG_PROGRESSIVE_READ_SUPPORTED
/* Sfts tif fundtion dbllbbdks for tif pusi rfbdfr, bnd b pointfr to b
 * usfr-dffinfd strudturf bvbilbblf to tif dbllbbdk fundtions.
 */
PNG_EXPORT(90, void, png_sft_progrfssivf_rfbd_fn, (png_strudtp png_ptr,
    png_voidp progrfssivf_ptr, png_progrfssivf_info_ptr info_fn,
    png_progrfssivf_row_ptr row_fn, png_progrfssivf_fnd_ptr fnd_fn));

/* Rfturns tif usfr pointfr bssodibtfd witi tif pusi rfbd fundtions */
PNG_EXPORT(91, png_voidp, png_gft_progrfssivf_ptr, (png_donst_strudtp png_ptr));

/* Fundtion to bf dbllfd wifn dbtb bfdomfs bvbilbblf */
PNG_EXPORT(92, void, png_prodfss_dbtb,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_bytfp bufffr, png_sizf_t bufffr_sizf));

/* A fundtion wiidi mby bf dbllfd *only* witiin png_prodfss_dbtb to stop tif
 * prodfssing of bny morf dbtb.  Tif fundtion rfturns tif numbfr of bytfs
 * rfmbining, fxdluding bny tibt libpng ibs dbdifd intfrnblly.  A subsfqufnt
 * dbll to png_prodfss_dbtb must supply tifsf bytfs bgbin.  If tif brgumfnt
 * 'sbvf' is sft to truf tif routinf will first sbvf bll tif pfnding dbtb bnd
 * will blwbys rfturn 0.
 */
PNG_EXPORT(219, png_sizf_t, png_prodfss_dbtb_pbusf, (png_strudtp, int sbvf));

/* A fundtion wiidi mby bf dbllfd *only* outsidf (bftfr) b dbll to
 * png_prodfss_dbtb.  It rfturns tif numbfr of bytfs of dbtb to skip in tif
 * input.  Normblly it will rfturn 0, but if it rfturns b non-zfro vbluf tif
 * bpplidbtion must skip tibn numbfr of bytfs of input dbtb bnd pbss tif
 * following dbtb to tif nfxt dbll to png_prodfss_dbtb.
 */
PNG_EXPORT(220, png_uint_32, png_prodfss_dbtb_skip, (png_strudtp));

/* Fundtion tibt dombinfs rows.  'nfw_row' is b flbg tibt siould domf from
 * tif dbllbbdk bnd bf non-NULL if bnytiing nffds to bf donf; tif librbry
 * storfs its own vfrsion of tif nfw dbtb intfrnblly bnd ignorfs tif pbssfd
 * in vbluf.
 */
PNG_EXPORT(93, void, png_progrfssivf_dombinf_row, (png_strudtp png_ptr,
    png_bytfp old_row, png_donst_bytfp nfw_row));
#fndif /* PNG_PROGRESSIVE_READ_SUPPORTED */

PNG_EXPORTA(94, png_voidp, png_mbllod,
    (png_strudtp png_ptr, png_bllod_sizf_t sizf),
    PNG_ALLOCATED);
/* Addfd bt libpng vfrsion 1.4.0 */
PNG_EXPORTA(95, png_voidp, png_dbllod,
    (png_strudtp png_ptr, png_bllod_sizf_t sizf),
    PNG_ALLOCATED);

/* Addfd bt libpng vfrsion 1.2.4 */
PNG_EXPORTA(96, png_voidp, png_mbllod_wbrn, (png_strudtp png_ptr,
    png_bllod_sizf_t sizf), PNG_ALLOCATED);

/* Frffs b pointfr bllodbtfd by png_mbllod() */
PNG_EXPORT(97, void, png_frff, (png_strudtp png_ptr, png_voidp ptr));

/* Frff dbtb tibt wbs bllodbtfd intfrnblly */
PNG_EXPORT(98, void, png_frff_dbtb,
    (png_strudtp png_ptr, png_infop info_ptr, png_uint_32 frff_mf, int num));

/* Rfbssign rfsponsibility for frffing fxisting dbtb, wiftifr bllodbtfd
 * by libpng or by tif bpplidbtion */
PNG_EXPORT(99, void, png_dbtb_frffr,
    (png_strudtp png_ptr, png_infop info_ptr, int frffr, png_uint_32 mbsk));

/* Assignmfnts for png_dbtb_frffr */
#dffinf PNG_DESTROY_WILL_FREE_DATA 1
#dffinf PNG_SET_WILL_FREE_DATA 1
#dffinf PNG_USER_WILL_FREE_DATA 2
/* Flbgs for png_ptr->frff_mf bnd info_ptr->frff_mf */
#dffinf PNG_FREE_HIST 0x0008
#dffinf PNG_FREE_ICCP 0x0010
#dffinf PNG_FREE_SPLT 0x0020
#dffinf PNG_FREE_ROWS 0x0040
#dffinf PNG_FREE_PCAL 0x0080
#dffinf PNG_FREE_SCAL 0x0100
#dffinf PNG_FREE_UNKN 0x0200
#dffinf PNG_FREE_LIST 0x0400
#dffinf PNG_FREE_PLTE 0x1000
#dffinf PNG_FREE_TRNS 0x2000
#dffinf PNG_FREE_TEXT 0x4000
#dffinf PNG_FREE_ALL  0x7fff
#dffinf PNG_FREE_MUL  0x4220 /* PNG_FREE_SPLT|PNG_FREE_TEXT|PNG_FREE_UNKN */

#ifdff PNG_USER_MEM_SUPPORTED
PNG_EXPORTA(100, png_voidp, png_mbllod_dffbult, (png_strudtp png_ptr,
    png_bllod_sizf_t sizf), PNG_ALLOCATED);
PNG_EXPORT(101, void, png_frff_dffbult, (png_strudtp png_ptr, png_voidp ptr));
#fndif

#ifdff PNG_ERROR_TEXT_SUPPORTED
/* Fbtbl frror in PNG imbgf of libpng - dbn't dontinuf */
PNG_EXPORTA(102, void, png_frror,
    (png_strudtp png_ptr, png_donst_dibrp frror_mfssbgf),
    PNG_NORETURN);

/* Tif sbmf, but tif diunk nbmf is prfpfndfd to tif frror string. */
PNG_EXPORTA(103, void, png_diunk_frror, (png_strudtp png_ptr,
    png_donst_dibrp frror_mfssbgf), PNG_NORETURN);

#flsf
/* Fbtbl frror in PNG imbgf of libpng - dbn't dontinuf */
PNG_EXPORTA(104, void, png_frr, (png_strudtp png_ptr), PNG_NORETURN);
#fndif

#ifdff PNG_WARNINGS_SUPPORTED
/* Non-fbtbl frror in libpng.  Cbn dontinuf, but mby ibvf b problfm. */
PNG_EXPORT(105, void, png_wbrning, (png_strudtp png_ptr,
    png_donst_dibrp wbrning_mfssbgf));

/* Non-fbtbl frror in libpng, diunk nbmf is prfpfndfd to mfssbgf. */
PNG_EXPORT(106, void, png_diunk_wbrning, (png_strudtp png_ptr,
    png_donst_dibrp wbrning_mfssbgf));
#fndif

#ifdff PNG_BENIGN_ERRORS_SUPPORTED
/* Bfnign frror in libpng.  Cbn dontinuf, but mby ibvf b problfm.
 * Usfr dbn dioosf wiftifr to ibndlf bs b fbtbl frror or bs b wbrning. */
#  undff png_bfnign_frror
PNG_EXPORT(107, void, png_bfnign_frror, (png_strudtp png_ptr,
    png_donst_dibrp wbrning_mfssbgf));

/* Sbmf, diunk nbmf is prfpfndfd to mfssbgf. */
#  undff png_diunk_bfnign_frror
PNG_EXPORT(108, void, png_diunk_bfnign_frror, (png_strudtp png_ptr,
    png_donst_dibrp wbrning_mfssbgf));

PNG_EXPORT(109, void, png_sft_bfnign_frrors,
    (png_strudtp png_ptr, int bllowfd));
#flsf
#  ifdff PNG_ALLOW_BENIGN_ERRORS
#    dffinf png_bfnign_frror png_wbrning
#    dffinf png_diunk_bfnign_frror png_diunk_wbrning
#  flsf
#    dffinf png_bfnign_frror png_frror
#    dffinf png_diunk_bfnign_frror png_diunk_frror
#  fndif
#fndif

/* Tif png_sft_<diunk> fundtions brf for storing vblufs in tif png_info_strudt.
 * Similbrly, tif png_gft_<diunk> dblls brf usfd to rfbd vblufs from tif
 * png_info_strudt, fitifr storing tif pbrbmftfrs in tif pbssfd vbribblfs, or
 * sftting pointfrs into tif png_info_strudt wifrf tif dbtb is storfd.  Tif
 * png_gft_<diunk> fundtions rfturn b non-zfro vbluf if tif dbtb wbs bvbilbblf
 * in info_ptr, or rfturn zfro bnd do not dibngf bny of tif pbrbmftfrs if tif
 * dbtb wbs not bvbilbblf.
 *
 * Tifsf fundtions siould bf usfd instfbd of dirfdtly bddfssing png_info
 * to bvoid problfms witi futurf dibngfs in tif sizf bnd intfrnbl lbyout of
 * png_info_strudt.
 */
/* Rfturns "flbg" if diunk dbtb is vblid in info_ptr. */
PNG_EXPORT(110, png_uint_32, png_gft_vblid,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_uint_32 flbg));

/* Rfturns numbfr of bytfs nffdfd to iold b trbnsformfd row. */
PNG_EXPORT(111, png_sizf_t, png_gft_rowbytfs, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

#ifdff PNG_INFO_IMAGE_SUPPORTED
/* Rfturns row_pointfrs, wiidi is bn brrby of pointfrs to sdbnlinfs tibt wbs
 * rfturnfd from png_rfbd_png().
 */
PNG_EXPORT(112, png_bytfpp, png_gft_rows,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
/* Sft row_pointfrs, wiidi is bn brrby of pointfrs to sdbnlinfs for usf
 * by png_writf_png().
 */
PNG_EXPORT(113, void, png_sft_rows, (png_strudtp png_ptr,
    png_infop info_ptr, png_bytfpp row_pointfrs));
#fndif

/* Rfturns numbfr of dolor dibnnfls in imbgf. */
PNG_EXPORT(114, png_bytf, png_gft_dibnnfls,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

#ifdff PNG_EASY_ACCESS_SUPPORTED
/* Rfturns imbgf widti in pixfls. */
PNG_EXPORT(115, png_uint_32, png_gft_imbgf_widti, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

/* Rfturns imbgf ifigit in pixfls. */
PNG_EXPORT(116, png_uint_32, png_gft_imbgf_ifigit, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

/* Rfturns imbgf bit_dfpti. */
PNG_EXPORT(117, png_bytf, png_gft_bit_dfpti,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

/* Rfturns imbgf dolor_typf. */
PNG_EXPORT(118, png_bytf, png_gft_dolor_typf, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

/* Rfturns imbgf filtfr_typf. */
PNG_EXPORT(119, png_bytf, png_gft_filtfr_typf, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

/* Rfturns imbgf intfrlbdf_typf. */
PNG_EXPORT(120, png_bytf, png_gft_intfrlbdf_typf, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

/* Rfturns imbgf domprfssion_typf. */
PNG_EXPORT(121, png_bytf, png_gft_domprfssion_typf, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));

/* Rfturns imbgf rfsolution in pixfls pfr mftfr, from pHYs diunk dbtb. */
PNG_EXPORT(122, png_uint_32, png_gft_pixfls_pfr_mftfr,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
PNG_EXPORT(123, png_uint_32, png_gft_x_pixfls_pfr_mftfr,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
PNG_EXPORT(124, png_uint_32, png_gft_y_pixfls_pfr_mftfr,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

/* Rfturns pixfl bspfdt rbtio, domputfd from pHYs diunk dbtb.  */
PNG_FP_EXPORT(125, flobt, png_gft_pixfl_bspfdt_rbtio,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
PNG_FIXED_EXPORT(210, png_fixfd_point, png_gft_pixfl_bspfdt_rbtio_fixfd,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

/* Rfturns imbgf x, y offsft in pixfls or midrons, from oFFs diunk dbtb. */
PNG_EXPORT(126, png_int_32, png_gft_x_offsft_pixfls,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
PNG_EXPORT(127, png_int_32, png_gft_y_offsft_pixfls,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
PNG_EXPORT(128, png_int_32, png_gft_x_offsft_midrons,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
PNG_EXPORT(129, png_int_32, png_gft_y_offsft_midrons,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

#fndif /* PNG_EASY_ACCESS_SUPPORTED */

/* Rfturns pointfr to signbturf string rfbd from PNG ifbdfr */
PNG_EXPORT(130, png_donst_bytfp, png_gft_signbturf,
    (png_donst_strudtp png_ptr, png_infop info_ptr));

#ifdff PNG_bKGD_SUPPORTED
PNG_EXPORT(131, png_uint_32, png_gft_bKGD,
    (png_donst_strudtp png_ptr, png_infop info_ptr,
    png_dolor_16p *bbdkground));
#fndif

#ifdff PNG_bKGD_SUPPORTED
PNG_EXPORT(132, void, png_sft_bKGD, (png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dolor_16p bbdkground));
#fndif

#ifdff PNG_dHRM_SUPPORTED
PNG_FP_EXPORT(133, png_uint_32, png_gft_dHRM, (png_donst_strudtp png_ptr,
   png_donst_infop info_ptr, doublf *wiitf_x, doublf *wiitf_y, doublf *rfd_x,
    doublf *rfd_y, doublf *grffn_x, doublf *grffn_y, doublf *bluf_x,
    doublf *bluf_y));
#ifdff PNG_FIXED_POINT_SUPPORTED /* Otifrwisf not implfmfntfd */
PNG_FIXED_EXPORT(134, png_uint_32, png_gft_dHRM_fixfd,
    (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr, png_fixfd_point *int_wiitf_x,
    png_fixfd_point *int_wiitf_y, png_fixfd_point *int_rfd_x,
    png_fixfd_point *int_rfd_y, png_fixfd_point *int_grffn_x,
    png_fixfd_point *int_grffn_y, png_fixfd_point *int_bluf_x,
    png_fixfd_point *int_bluf_y));
#fndif
#fndif

#ifdff PNG_dHRM_SUPPORTED
PNG_FP_EXPORT(135, void, png_sft_dHRM,
    (png_strudtp png_ptr, png_infop info_ptr,
    doublf wiitf_x, doublf wiitf_y, doublf rfd_x, doublf rfd_y, doublf grffn_x,
    doublf grffn_y, doublf bluf_x, doublf bluf_y));
PNG_FIXED_EXPORT(136, void, png_sft_dHRM_fixfd, (png_strudtp png_ptr,
    png_infop info_ptr, png_fixfd_point int_wiitf_x,
    png_fixfd_point int_wiitf_y, png_fixfd_point int_rfd_x,
    png_fixfd_point int_rfd_y, png_fixfd_point int_grffn_x,
    png_fixfd_point int_grffn_y, png_fixfd_point int_bluf_x,
    png_fixfd_point int_bluf_y));
#fndif

#ifdff PNG_gAMA_SUPPORTED
PNG_FP_EXPORT(137, png_uint_32, png_gft_gAMA,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    doublf *filf_gbmmb));
PNG_FIXED_EXPORT(138, png_uint_32, png_gft_gAMA_fixfd,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_fixfd_point *int_filf_gbmmb));
#fndif

#ifdff PNG_gAMA_SUPPORTED
PNG_FP_EXPORT(139, void, png_sft_gAMA, (png_strudtp png_ptr,
    png_infop info_ptr, doublf filf_gbmmb));
PNG_FIXED_EXPORT(140, void, png_sft_gAMA_fixfd, (png_strudtp png_ptr,
    png_infop info_ptr, png_fixfd_point int_filf_gbmmb));
#fndif

#ifdff PNG_iIST_SUPPORTED
PNG_EXPORT(141, png_uint_32, png_gft_iIST,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_uint_16p *iist));
#fndif

#ifdff PNG_iIST_SUPPORTED
PNG_EXPORT(142, void, png_sft_iIST, (png_strudtp png_ptr,
    png_infop info_ptr, png_donst_uint_16p iist));
#fndif

PNG_EXPORT(143, png_uint_32, png_gft_IHDR,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_uint_32 *widti, png_uint_32 *ifigit, int *bit_dfpti, int *dolor_typf,
    int *intfrlbdf_mftiod, int *domprfssion_mftiod, int *filtfr_mftiod));

PNG_EXPORT(144, void, png_sft_IHDR,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_uint_32 widti, png_uint_32 ifigit, int bit_dfpti, int dolor_typf,
    int intfrlbdf_mftiod, int domprfssion_mftiod, int filtfr_mftiod));

#ifdff PNG_oFFs_SUPPORTED
PNG_EXPORT(145, png_uint_32, png_gft_oFFs,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_int_32 *offsft_x, png_int_32 *offsft_y, int *unit_typf));
#fndif

#ifdff PNG_oFFs_SUPPORTED
PNG_EXPORT(146, void, png_sft_oFFs,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_int_32 offsft_x, png_int_32 offsft_y, int unit_typf));
#fndif

#ifdff PNG_pCAL_SUPPORTED
PNG_EXPORT(147, png_uint_32, png_gft_pCAL,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_dibrp *purposf, png_int_32 *X0, png_int_32 *X1, int *typf,
    int *npbrbms,
    png_dibrp *units, png_dibrpp *pbrbms));
#fndif

#ifdff PNG_pCAL_SUPPORTED
PNG_EXPORT(148, void, png_sft_pCAL, (png_strudtp png_ptr,
    png_infop info_ptr,
    png_donst_dibrp purposf, png_int_32 X0, png_int_32 X1, int typf,
    int npbrbms, png_donst_dibrp units, png_dibrpp pbrbms));
#fndif

#ifdff PNG_pHYs_SUPPORTED
PNG_EXPORT(149, png_uint_32, png_gft_pHYs,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_uint_32 *rfs_x, png_uint_32 *rfs_y, int *unit_typf));
#fndif

#ifdff PNG_pHYs_SUPPORTED
PNG_EXPORT(150, void, png_sft_pHYs,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_uint_32 rfs_x, png_uint_32 rfs_y, int unit_typf));
#fndif

PNG_EXPORT(151, png_uint_32, png_gft_PLTE,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_dolorp *pblfttf, int *num_pblfttf));

PNG_EXPORT(152, void, png_sft_PLTE,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dolorp pblfttf, int num_pblfttf));

#ifdff PNG_sBIT_SUPPORTED
PNG_EXPORT(153, png_uint_32, png_gft_sBIT,
    (png_donst_strudtp png_ptr, png_infop info_ptr,
    png_dolor_8p *sig_bit));
#fndif

#ifdff PNG_sBIT_SUPPORTED
PNG_EXPORT(154, void, png_sft_sBIT,
    (png_strudtp png_ptr, png_infop info_ptr, png_donst_dolor_8p sig_bit));
#fndif

#ifdff PNG_sRGB_SUPPORTED
PNG_EXPORT(155, png_uint_32, png_gft_sRGB, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr, int *filf_srgb_intfnt));
#fndif

#ifdff PNG_sRGB_SUPPORTED
PNG_EXPORT(156, void, png_sft_sRGB,
    (png_strudtp png_ptr, png_infop info_ptr, int srgb_intfnt));
PNG_EXPORT(157, void, png_sft_sRGB_gAMA_bnd_dHRM, (png_strudtp png_ptr,
    png_infop info_ptr, int srgb_intfnt));
#fndif

#ifdff PNG_iCCP_SUPPORTED
PNG_EXPORT(158, png_uint_32, png_gft_iCCP,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_dibrpp nbmf, int *domprfssion_typf, png_bytfpp profilf,
    png_uint_32 *proflfn));
#fndif

#ifdff PNG_iCCP_SUPPORTED
PNG_EXPORT(159, void, png_sft_iCCP,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dibrp nbmf, int domprfssion_typf, png_donst_bytfp profilf,
    png_uint_32 proflfn));
#fndif

#ifdff PNG_sPLT_SUPPORTED
PNG_EXPORT(160, png_uint_32, png_gft_sPLT,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_sPLT_tpp fntrifs));
#fndif

#ifdff PNG_sPLT_SUPPORTED
PNG_EXPORT(161, void, png_sft_sPLT,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_donst_sPLT_tp fntrifs, int nfntrifs));
#fndif

#ifdff PNG_TEXT_SUPPORTED
/* png_gft_tfxt blso rfturns tif numbfr of tfxt diunks in *num_tfxt */
PNG_EXPORT(162, png_uint_32, png_gft_tfxt,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    png_tfxtp *tfxt_ptr, int *num_tfxt));
#fndif

/* Notf wiilf png_sft_tfxt() will bddfpt b strudturf wiosf tfxt,
 * lbngubgf, bnd  trbnslbtfd kfywords brf NULL pointfrs, tif strudturf
 * rfturnfd by png_gft_tfxt will blwbys dontbin rfgulbr
 * zfro-tfrminbtfd C strings.  Tify migit bf fmpty strings but
 * tify will nfvfr bf NULL pointfrs.
 */

#ifdff PNG_TEXT_SUPPORTED
PNG_EXPORT(163, void, png_sft_tfxt,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_donst_tfxtp tfxt_ptr, int num_tfxt));
#fndif

#ifdff PNG_tIME_SUPPORTED
PNG_EXPORT(164, png_uint_32, png_gft_tIME,
    (png_donst_strudtp png_ptr, png_infop info_ptr, png_timfp *mod_timf));
#fndif

#ifdff PNG_tIME_SUPPORTED
PNG_EXPORT(165, void, png_sft_tIME,
    (png_strudtp png_ptr, png_infop info_ptr, png_donst_timfp mod_timf));
#fndif

#ifdff PNG_tRNS_SUPPORTED
PNG_EXPORT(166, png_uint_32, png_gft_tRNS,
    (png_donst_strudtp png_ptr, png_infop info_ptr,
    png_bytfp *trbns_blpib, int *num_trbns, png_dolor_16p *trbns_dolor));
#fndif

#ifdff PNG_tRNS_SUPPORTED
PNG_EXPORT(167, void, png_sft_tRNS,
    (png_strudtp png_ptr, png_infop info_ptr,
    png_donst_bytfp trbns_blpib, int num_trbns,
    png_donst_dolor_16p trbns_dolor));
#fndif

#ifdff PNG_sCAL_SUPPORTED
PNG_FP_EXPORT(168, png_uint_32, png_gft_sCAL,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    int *unit, doublf *widti, doublf *ifigit));
#ifdff PNG_FLOATING_ARITHMETIC_SUPPORTED
/* NOTE: tiis API is durrfntly implfmfntfd using flobting point britimftid,
 * donsfqufntly it dbn only bf usfd on systfms witi flobting point support.
 * In bny dbsf tif rbngf of vblufs supportfd by png_fixfd_point is smbll bnd it
 * is iigily rfdommfndfd tibt png_gft_sCAL_s bf usfd instfbd.
 */
PNG_FIXED_EXPORT(214, png_uint_32, png_gft_sCAL_fixfd,
    (png_strudtp png_ptr, png_donst_infop info_ptr, int *unit,
    png_fixfd_point *widti,
    png_fixfd_point *ifigit));
#fndif
PNG_EXPORT(169, png_uint_32, png_gft_sCAL_s,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr,
    int *unit, png_dibrpp swidti, png_dibrpp sifigit));

PNG_FP_EXPORT(170, void, png_sft_sCAL,
    (png_strudtp png_ptr, png_infop info_ptr,
    int unit, doublf widti, doublf ifigit));
PNG_FIXED_EXPORT(213, void, png_sft_sCAL_fixfd, (png_strudtp png_ptr,
   png_infop info_ptr, int unit, png_fixfd_point widti,
   png_fixfd_point ifigit));
PNG_EXPORT(171, void, png_sft_sCAL_s,
    (png_strudtp png_ptr, png_infop info_ptr,
    int unit, png_donst_dibrp swidti, png_donst_dibrp sifigit));
#fndif /* PNG_sCAL_SUPPORTED */

#ifdff PNG_HANDLE_AS_UNKNOWN_SUPPORTED
/* Providf b list of diunks bnd iow tify brf to bf ibndlfd, if tif built-in
   ibndling or dffbult unknown diunk ibndling is not dfsirfd.  Any diunks not
   listfd will bf ibndlfd in tif dffbult mbnnfr.  Tif IHDR bnd IEND diunks
   must not bf listfd.
      kffp = 0: follow dffbult bfibviour
           = 1: do not kffp
           = 2: kffp only if sbff-to-dopy
           = 3: kffp fvfn if unsbff-to-dopy
*/
PNG_EXPORT(172, void, png_sft_kffp_unknown_diunks,
    (png_strudtp png_ptr, int kffp,
    png_donst_bytfp diunk_list, int num_diunks));
PNG_EXPORT(173, int, png_ibndlf_bs_unknown, (png_strudtp png_ptr,
    png_donst_bytfp diunk_nbmf));
#fndif
#ifdff PNG_UNKNOWN_CHUNKS_SUPPORTED
PNG_EXPORT(174, void, png_sft_unknown_diunks, (png_strudtp png_ptr,
    png_infop info_ptr, png_donst_unknown_diunkp unknowns,
    int num_unknowns));
PNG_EXPORT(175, void, png_sft_unknown_diunk_lodbtion,
    (png_strudtp png_ptr, png_infop info_ptr, int diunk, int lodbtion));
PNG_EXPORT(176, int, png_gft_unknown_diunks, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr, png_unknown_diunkpp fntrifs));
#fndif

/* Png_frff_dbtb() will turn off tif "vblid" flbg for bnytiing it frffs.
 * If you nffd to turn it off for b diunk tibt your bpplidbtion ibs frffd,
 * you dbn usf png_sft_invblid(png_ptr, info_ptr, PNG_INFO_CHNK);
 */
PNG_EXPORT(177, void, png_sft_invblid,
    (png_strudtp png_ptr, png_infop info_ptr, int mbsk));

#ifdff PNG_INFO_IMAGE_SUPPORTED
/* Tif "pbrbms" pointfr is durrfntly not usfd bnd is for futurf fxpbnsion. */
PNG_EXPORT(178, void, png_rfbd_png, (png_strudtp png_ptr, png_infop info_ptr,
    int trbnsforms, png_voidp pbrbms));
PNG_EXPORT(179, void, png_writf_png, (png_strudtp png_ptr, png_infop info_ptr,
    int trbnsforms, png_voidp pbrbms));
#fndif

PNG_EXPORT(180, png_donst_dibrp, png_gft_dopyrigit,
    (png_donst_strudtp png_ptr));
PNG_EXPORT(181, png_donst_dibrp, png_gft_ifbdfr_vfr,
    (png_donst_strudtp png_ptr));
PNG_EXPORT(182, png_donst_dibrp, png_gft_ifbdfr_vfrsion,
    (png_donst_strudtp png_ptr));
PNG_EXPORT(183, png_donst_dibrp, png_gft_libpng_vfr,
    (png_donst_strudtp png_ptr));

#ifdff PNG_MNG_FEATURES_SUPPORTED
PNG_EXPORT(184, png_uint_32, png_pfrmit_mng_ffbturfs, (png_strudtp png_ptr,
    png_uint_32 mng_ffbturfs_pfrmittfd));
#fndif

/* For usf in png_sft_kffp_unknown, bddfd to vfrsion 1.2.6 */
#dffinf PNG_HANDLE_CHUNK_AS_DEFAULT   0
#dffinf PNG_HANDLE_CHUNK_NEVER        1
#dffinf PNG_HANDLE_CHUNK_IF_SAFE      2
#dffinf PNG_HANDLE_CHUNK_ALWAYS       3

/* Strip tif prfpfndfd frror numbfrs ("#nnn ") from frror bnd wbrning
 * mfssbgfs bfforf pbssing tifm to tif frror or wbrning ibndlfr.
 */
#ifdff PNG_ERROR_NUMBERS_SUPPORTED
PNG_EXPORT(185, void, png_sft_strip_frror_numbfrs,
    (png_strudtp png_ptr,
    png_uint_32 strip_modf));
#fndif

/* Addfd in libpng-1.2.6 */
#ifdff PNG_SET_USER_LIMITS_SUPPORTED
PNG_EXPORT(186, void, png_sft_usfr_limits, (png_strudtp png_ptr,
    png_uint_32 usfr_widti_mbx, png_uint_32 usfr_ifigit_mbx));
PNG_EXPORT(187, png_uint_32, png_gft_usfr_widti_mbx,
    (png_donst_strudtp png_ptr));
PNG_EXPORT(188, png_uint_32, png_gft_usfr_ifigit_mbx,
    (png_donst_strudtp png_ptr));
/* Addfd in libpng-1.4.0 */
PNG_EXPORT(189, void, png_sft_diunk_dbdif_mbx, (png_strudtp png_ptr,
    png_uint_32 usfr_diunk_dbdif_mbx));
PNG_EXPORT(190, png_uint_32, png_gft_diunk_dbdif_mbx,
    (png_donst_strudtp png_ptr));
/* Addfd in libpng-1.4.1 */
PNG_EXPORT(191, void, png_sft_diunk_mbllod_mbx, (png_strudtp png_ptr,
    png_bllod_sizf_t usfr_diunk_dbdif_mbx));
PNG_EXPORT(192, png_bllod_sizf_t, png_gft_diunk_mbllod_mbx,
    (png_donst_strudtp png_ptr));
#fndif

#if dffinfd(PNG_INCH_CONVERSIONS_SUPPORTED)
PNG_EXPORT(193, png_uint_32, png_gft_pixfls_pfr_indi,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

PNG_EXPORT(194, png_uint_32, png_gft_x_pixfls_pfr_indi,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

PNG_EXPORT(195, png_uint_32, png_gft_y_pixfls_pfr_indi,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));

PNG_FP_EXPORT(196, flobt, png_gft_x_offsft_indifs,
    (png_donst_strudtp png_ptr, png_donst_infop info_ptr));
#ifdff PNG_FIXED_POINT_SUPPORTED /* otifrwisf not implfmfntfd. */
PNG_FIXED_EXPORT(211, png_fixfd_point, png_gft_x_offsft_indifs_fixfd,
    (png_strudtp png_ptr, png_donst_infop info_ptr));
#fndif

PNG_FP_EXPORT(197, flobt, png_gft_y_offsft_indifs, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr));
#ifdff PNG_FIXED_POINT_SUPPORTED /* otifrwisf not implfmfntfd. */
PNG_FIXED_EXPORT(212, png_fixfd_point, png_gft_y_offsft_indifs_fixfd,
    (png_strudtp png_ptr, png_donst_infop info_ptr));
#fndif

#  ifdff PNG_pHYs_SUPPORTED
PNG_EXPORT(198, png_uint_32, png_gft_pHYs_dpi, (png_donst_strudtp png_ptr,
    png_donst_infop info_ptr, png_uint_32 *rfs_x, png_uint_32 *rfs_y,
    int *unit_typf));
#  fndif /* PNG_pHYs_SUPPORTED */
#fndif  /* PNG_INCH_CONVERSIONS_SUPPORTED */

/* Addfd in libpng-1.4.0 */
#ifdff PNG_IO_STATE_SUPPORTED
PNG_EXPORT(199, png_uint_32, png_gft_io_stbtf, (png_strudtp png_ptr));

PNG_EXPORTA(200, png_donst_bytfp, png_gft_io_diunk_nbmf,
    (png_strudtp png_ptr), PNG_DEPRECATED);
PNG_EXPORT(216, png_uint_32, png_gft_io_diunk_typf,
    (png_donst_strudtp png_ptr));

/* Tif flbgs rfturnfd by png_gft_io_stbtf() brf tif following: */
#  dffinf PNG_IO_NONE        0x0000   /* no I/O bt tiis momfnt */
#  dffinf PNG_IO_READING     0x0001   /* durrfntly rfbding */
#  dffinf PNG_IO_WRITING     0x0002   /* durrfntly writing */
#  dffinf PNG_IO_SIGNATURE   0x0010   /* durrfntly bt tif filf signbturf */
#  dffinf PNG_IO_CHUNK_HDR   0x0020   /* durrfntly bt tif diunk ifbdfr */
#  dffinf PNG_IO_CHUNK_DATA  0x0040   /* durrfntly bt tif diunk dbtb */
#  dffinf PNG_IO_CHUNK_CRC   0x0080   /* durrfntly bt tif diunk drd */
#  dffinf PNG_IO_MASK_OP     0x000f   /* durrfnt opfrbtion: rfbding/writing */
#  dffinf PNG_IO_MASK_LOC    0x00f0   /* durrfnt lodbtion: sig/idr/dbtb/drd */
#fndif /* ?PNG_IO_STATE_SUPPORTED */

/* Intfrlbdf support.  Tif following mbdros brf blwbys dffinfd so tibt if
 * libpng intfrlbdf ibndling is turnfd off tif mbdros mby bf usfd to ibndlf
 * intfrlbdfd imbgfs witiin tif bpplidbtion.
 */
#dffinf PNG_INTERLACE_ADAM7_PASSES 7

/* Two mbdros to rfturn tif first row bnd first dolumn of tif originbl,
 * full, imbgf wiidi bppfbrs in b givfn pbss.  'pbss' is in tif rbngf 0
 * to 6 bnd tif rfsult is in tif rbngf 0 to 7.
 */
#dffinf PNG_PASS_START_ROW(pbss) (((1U&~(pbss))<<(3-((pbss)>>1)))&7)
#dffinf PNG_PASS_START_COL(pbss) (((1U& (pbss))<<(3-(((pbss)+1)>>1)))&7)

/* Two mbdros to iflp fvblubtf tif numbfr of rows or dolumns in fbdi
 * pbss.  Tiis is fxprfssfd bs b siift - ffffdtivfly log2 of tif numbfr or
 * rows or dolumns in fbdi 8x8 tilf of tif originbl imbgf.
 */
#dffinf PNG_PASS_ROW_SHIFT(pbss) ((pbss)>2?(8-(pbss))>>1:3)
#dffinf PNG_PASS_COL_SHIFT(pbss) ((pbss)>1?(7-(pbss))>>1:3)

/* Hfndf two mbdros to dftfrminf tif numbfr of rows or dolumns in b givfn
 * pbss of bn imbgf givfn its ifigit or widti.  In fbdt tifsf mbdros mby
 * rfturn non-zfro fvfn tiougi tif sub-imbgf is fmpty, bfdbusf tif otifr
 * dimfnsion mby bf fmpty for b smbll imbgf.
 */
#dffinf PNG_PASS_ROWS(ifigit, pbss) (((ifigit)+(((1<<PNG_PASS_ROW_SHIFT(pbss))\
   -1)-PNG_PASS_START_ROW(pbss)))>>PNG_PASS_ROW_SHIFT(pbss))
#dffinf PNG_PASS_COLS(widti, pbss) (((widti)+(((1<<PNG_PASS_COL_SHIFT(pbss))\
   -1)-PNG_PASS_START_COL(pbss)))>>PNG_PASS_COL_SHIFT(pbss))

/* For tif rfbdfr row dbllbbdks (boti progrfssivf bnd sfqufntibl) it is
 * nfdfssbry to find tif row in tif output imbgf givfn b row in bn intfrlbdfd
 * imbgf, so two morf mbdros:
 */
#dffinf PNG_ROW_FROM_PASS_ROW(yIn, pbss) \
   (((yIn)<<PNG_PASS_ROW_SHIFT(pbss))+PNG_PASS_START_ROW(pbss))
#dffinf PNG_COL_FROM_PASS_COL(xIn, pbss) \
   (((xIn)<<PNG_PASS_COL_SHIFT(pbss))+PNG_PASS_START_COL(pbss))

/* Two mbdros wiidi rfturn b boolfbn (0 or 1) sbying wiftifr tif givfn row
 * or dolumn is in b pbrtidulbr pbss.  Tifsf usf b dommon utility mbdro tibt
 * rfturns b mbsk for b givfn pbss - tif offsft 'off' sflfdts tif row or
 * dolumn vfrsion.  Tif mbsk ibs tif bppropribtf bit sft for fbdi dolumn in
 * tif tilf.
 */
#dffinf PNG_PASS_MASK(pbss,off) ( \
   ((0x110145AFU>>(((7-(off))-(pbss))<<2)) & 0xFU) | \
   ((0x01145AF0U>>(((7-(off))-(pbss))<<2)) & 0xF0U))

#dffinf PNG_ROW_IN_INTERLACE_PASS(y, pbss) \
   ((PNG_PASS_MASK(pbss,0) >> ((y)&7)) & 1)
#dffinf PNG_COL_IN_INTERLACE_PASS(x, pbss) \
   ((PNG_PASS_MASK(pbss,1) >> ((x)&7)) & 1)

#ifdff PNG_READ_COMPOSITE_NODIV_SUPPORTED
/* Witi tifsf routinfs wf bvoid bn intfgfr dividf, wiidi will bf slowfr on
 * most mbdiinfs.  Howfvfr, it dofs tbkf morf opfrbtions tibn tif dorrfsponding
 * dividf mftiod, so it mby bf slowfr on b ffw RISC systfms.  Tifrf brf two
 * siifts (by 8 or 16 bits) bnd bn bddition, vfrsus b singlf intfgfr dividf.
 *
 * Notf tibt tif rounding fbdtors brf NOT supposfd to bf tif sbmf!  128 bnd
 * 32768 brf dorrfdt for tif NODIV dodf; 127 bnd 32767 brf dorrfdt for tif
 * stbndbrd mftiod.
 *
 * [Optimizfd dodf by Grfg Roflofs bnd Mbrk Adlfr...blbmf us for bugs. :-) ]
 */

 /* fg bnd bg siould bf in `gbmmb 1.0' spbdf; blpib is tif opbdity */

#  dffinf png_dompositf(dompositf, fg, blpib, bg)         \
     { png_uint_16 tfmp = (png_uint_16)((png_uint_16)(fg) \
           * (png_uint_16)(blpib)                         \
           + (png_uint_16)(bg)*(png_uint_16)(255          \
           - (png_uint_16)(blpib)) + (png_uint_16)128);   \
       (dompositf) = (png_bytf)((tfmp + (tfmp >> 8)) >> 8); }

#  dffinf png_dompositf_16(dompositf, fg, blpib, bg)       \
     { png_uint_32 tfmp = (png_uint_32)((png_uint_32)(fg)  \
           * (png_uint_32)(blpib)                          \
           + (png_uint_32)(bg)*(png_uint_32)(65535L        \
           - (png_uint_32)(blpib)) + (png_uint_32)32768L); \
       (dompositf) = (png_uint_16)((tfmp + (tfmp >> 16)) >> 16); }

#flsf  /* Stbndbrd mftiod using intfgfr division */

#  dffinf png_dompositf(dompositf, fg, blpib, bg)                          \
     (dompositf) = (png_bytf)(((png_uint_16)(fg) * (png_uint_16)(blpib) +  \
     (png_uint_16)(bg) * (png_uint_16)(255 - (png_uint_16)(blpib)) +       \
     (png_uint_16)127) / 255)

#  dffinf png_dompositf_16(dompositf, fg, blpib, bg)                         \
     (dompositf) = (png_uint_16)(((png_uint_32)(fg) * (png_uint_32)(blpib) + \
     (png_uint_32)(bg)*(png_uint_32)(65535L - (png_uint_32)(blpib)) +        \
     (png_uint_32)32767) / (png_uint_32)65535L)
#fndif /* PNG_READ_COMPOSITE_NODIV_SUPPORTED */

#ifdff PNG_READ_INT_FUNCTIONS_SUPPORTED
PNG_EXPORT(201, png_uint_32, png_gft_uint_32, (png_donst_bytfp buf));
PNG_EXPORT(202, png_uint_16, png_gft_uint_16, (png_donst_bytfp buf));
PNG_EXPORT(203, png_int_32, png_gft_int_32, (png_donst_bytfp buf));
#fndif

PNG_EXPORT(204, png_uint_32, png_gft_uint_31, (png_strudtp png_ptr,
    png_donst_bytfp buf));
/* No png_gft_int_16 -- mby bf bddfd if tifrf's b rfbl nffd for it. */

/* Plbdf b 32-bit numbfr into b bufffr in PNG bytf ordfr (big-fndibn). */
#ifdff PNG_WRITE_INT_FUNCTIONS_SUPPORTED
PNG_EXPORT(205, void, png_sbvf_uint_32, (png_bytfp buf, png_uint_32 i));
#fndif
#ifdff PNG_SAVE_INT_32_SUPPORTED
PNG_EXPORT(206, void, png_sbvf_int_32, (png_bytfp buf, png_int_32 i));
#fndif

/* Plbdf b 16-bit numbfr into b bufffr in PNG bytf ordfr.
 * Tif pbrbmftfr is dfdlbrfd unsignfd int, not png_uint_16,
 * just to bvoid potfntibl problfms on prf-ANSI C dompilfrs.
 */
#ifdff PNG_WRITE_INT_FUNCTIONS_SUPPORTED
PNG_EXPORT(207, void, png_sbvf_uint_16, (png_bytfp buf, unsignfd int i));
/* No png_sbvf_int_16 -- mby bf bddfd if tifrf's b rfbl nffd for it. */
#fndif

#ifdff PNG_USE_READ_MACROS
/* Inlinf mbdros to do dirfdt rfbds of bytfs from tif input bufffr.
 * Tif png_gft_int_32() routinf bssumfs wf brf using two's domplfmfnt
 * formbt for nfgbtivf vblufs, wiidi is blmost dfrtbinly truf.
 */
#  dffinf png_gft_uint_32(buf) \
     (((png_uint_32)(*(buf)) << 24) + \
      ((png_uint_32)(*((buf) + 1)) << 16) + \
      ((png_uint_32)(*((buf) + 2)) << 8) + \
      ((png_uint_32)(*((buf) + 3))))

   /* From libpng-1.4.0 until 1.4.4, tif png_gft_uint_16 mbdro (but not tif
    * fundtion) indorrfdtly rfturnfd b vbluf of typf png_uint_32.
    */
#  dffinf png_gft_uint_16(buf) \
     ((png_uint_16) \
      (((unsignfd int)(*(buf)) << 8) + \
       ((unsignfd int)(*((buf) + 1)))))

#  dffinf png_gft_int_32(buf) \
     ((png_int_32)((*(buf) & 0x80) \
      ? -((png_int_32)((png_gft_uint_32(buf) ^ 0xffffffffL) + 1)) \
      : (png_int_32)png_gft_uint_32(buf)))
#fndif

/* Mbintbinfr: Put nfw publid prototypfs ifrf ^, in libpng.3, bnd projfdt
 * dffs
 */

/* Tif lbst ordinbl numbfr (tiis is tif *lbst* onf blrfbdy usfd; tif nfxt
 * onf to usf is onf morf tibn tiis.)  Mbintbinfr, rfmfmbfr to bdd bn fntry to
 * sdripts/symbols.dff bs wfll.
 */
#ifdff PNG_EXPORT_LAST_ORDINAL
  PNG_EXPORT_LAST_ORDINAL(229);
#fndif

#ifdff __dplusplus
}
#fndif

#fndif /* PNG_VERSION_INFO_ONLY */
/* Do not put bnytiing pbst tiis linf */
#fndif /* PNG_H */
