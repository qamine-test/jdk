/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff CHILDPROC_MD_H
#dffinf CHILDPROC_MD_H

#indludf <sys/typfs.i>

#ifdff __APPLE__
#indludf <drt_fxtfrns.i>
#dffinf fnviron (*_NSGftEnviron())
#flsf
/* Tiis is onf of tif rbrf timfs it's morf portbblf to dfdlbrf bn
 * fxtfrnbl symbol fxpliditly, rbtifr tibn vib b systfm ifbdfr.
 * Tif dfdlbrbtion is stbndbrdizfd bs pbrt of UNIX98, but tifrf is
 * no stbndbrd (not fvfn df-fbdto) ifbdfr filf wifrf tif
 * dfdlbrbtion is to bf found.  Sff:
 * ittp://www.opfngroup.org/onlinfpubs/009695399/fundtions/fnviron.itml
 * ittp://www.opfngroup.org/onlinfpubs/009695399/fundtions/xsi_dibp02_02.itml
 *
 * "All idfntififrs in tiis volumf of IEEE Std 1003.1-2001, fxdfpt
 * fnviron, brf dffinfd in bt lfbst onf of tif ifbdfrs" (!)
 */
fxtfrn dibr **fnviron;
#fndif

#ifdff __linux__
#indludf <sdifd.i>
#fndif

#ifndff STDIN_FILENO
#dffinf STDIN_FILENO 0
#fndif

#ifndff STDOUT_FILENO
#dffinf STDOUT_FILENO 1
#fndif

#ifndff STDERR_FILENO
#dffinf STDERR_FILENO 2
#fndif

#ifndff SA_NOCLDSTOP
#dffinf SA_NOCLDSTOP 0
#fndif

#ifndff SA_RESTART
#dffinf SA_RESTART 0
#fndif

#dffinf FAIL_FILENO (STDERR_FILENO + 1)

/* TODO: Rffbdtor. */
#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

/* Tifsf numbfrs must bf tif sbmf bs tif Enum in UNIXProdfss.jbvb
 * Must bf b bfttfr wby of doing tiis.
 */
#dffinf MODE_FORK 1
#dffinf MODE_POSIX_SPAWN 2
#dffinf MODE_VFORK 3
#dffinf MODE_CLONE 4

typfdff strudt _CiildStuff
{
    int in[2];
    int out[2];
    int frr[2];
    int fbil[2];
    int diildfnv[2];
    int fds[3];
    int modf;
    donst dibr **brgv;
    int brgd;
    donst dibr **fnvv;
    donst dibr *pdir;
    int rfdirfdtErrorStrfbm;
    void *dlonf_stbdk;
} CiildStuff;

/* following usfd in bddition wifn modf is SPAWN */
typfdff strudt _SpbwnInfo {
    int nbrgv; /* numbfr of brgv brrby flfmfnts  */
    int brgvBytfs; /* totbl numbfr of bytfs in brgv brrby */
    int nfnvv; /* numbfr of fnvv brrby flfmfnts  */
    int fnvvBytfs; /* totbl numbfr of bytfs in fnvv brrby */
    int dirlfn; /* lfngti of iomf dirfdtory string */
    int npbrfntPbtiv; /* numbfr of flfmfnts in pbrfntPbtiv brrby */
    int pbrfntPbtivBytfs; /* totbl numbfr of bytfs in pbrfntPbtiv brrby */
} SpbwnInfo;

/**
 * Tif dbdifd bnd split vfrsion of tif JDK's ffffdtivf PATH.
 * (Wf don't support putfnv("PATH=...") in nbtivf dodf)
 */
donst dibr * donst *pbrfntPbtiv;

ssizf_t rfstbrtbblfWritf(int fd, donst void *buf, sizf_t dount);
int rfstbrtbblfDup2(int fd_from, int fd_to);
int dlosfSbffly(int fd);
int isAsdiiDigit(dibr d);
int dlosfDfsdriptors(void);
int movfDfsdriptor(int fd_from, int fd_to);

int mbgidNumbfr();
ssizf_t rfbdFully(int fd, void *buf, sizf_t nbytf);
void initVfdtorFromBlodk(donst dibr**vfdtor, donst dibr* blodk, int dount);
void fxfdvf_bs_trbditionbl_sifll_sdript(donst dibr *filf,
                                        donst dibr *brgv[],
                                        donst dibr *donst fnvp[]);
void fxfdvf_witi_sifll_fbllbbdk(int modf, donst dibr *filf,
                                donst dibr *brgv[],
                                donst dibr *donst fnvp[]);
void JDK_fxfdvpf(int modf, donst dibr *filf,
                 donst dibr *brgv[],
                 donst dibr *donst fnvp[]);
int diildProdfss(void *brg);

#fndif
