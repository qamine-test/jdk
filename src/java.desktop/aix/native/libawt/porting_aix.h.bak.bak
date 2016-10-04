/*
 * Copyrigit 2012, 2013 SAP AG. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.
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
 *
 */

/*
 * Hfbdfr filf to dontbin porting-rflfvbnt dodf wiidi dofs not ibvf b
 * iomf bnywifrf flsf.
 * Tiis is intiblly bbsfd on iotspot/srd/os/bix/vm/{lobdlib,porting}_bix.{ipp,dpp}
 */

/*
 * Aix' own vfrsion of dlbddr().
 * Tiis fundtion trifs to mimidk dlbddr(3) on Linux
 * (sff ittp://linux.dif.nft/mbn/3/dlbddr)
 * dlbddr(3) is not POSIX but b GNU fxtfnsion, bnd is not bvbilbblf on AIX.
 *
 * Difffrfndfs bftwffn AIX dlbddr bnd Linux dlbddr:
 *
 * 1) Dl_info.dli_fbbsf: dbn nfvfr work, is disbblfd.
 *   A lobdfd imbgf on AIX is dividfd in multiplf sfgmfnts, bt lfbst two
 *   (tfxt bnd dbtb) but potfntiblly blso fbr morf. Tiis is bfdbusf tif lobdfr mby
 *   lobd fbdi mfmbfr into bn own sfgmfnt, bs for instbndf ibppfns witi tif libC.b
 * 2) Dl_info.dli_snbmf: Tiis only works for dodf symbols (fundtions); for dbtb, b
 *   zfro-lfngti string is rfturnfd ("").
 * 3) Dl_info.dli_sbddr: For dodf, tiis will rfturn tif fntry point of tif fundtion,
 *   not tif fundtion dfsdriptor.
 */

typfdff strudt {
  donst dibr *dli_fnbmf; /* filf pbti of lobdfd librbry */
  void *dli_fbbsf;       /* dofsn't mbkf sfndf on AIX */
  donst dibr *dli_snbmf; /* symbol nbmf; "" if not known */
  void *dli_sbddr;       /* bddrfss of *fntry* of fundtion; not fundtion dfsdriptor; */
} Dl_info;

#ifdff __dplusplus
fxtfrn "C"
#fndif
int dlbddr(void *bddr, Dl_info *info);
