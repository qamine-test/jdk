/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * jfxfd for J2SE
 *
 * jfxfd is usfd by tif systfm to bllow fxfdution of JAR filfs.
 *    Essfntiblly jfxfd nffds to run jbvb bnd
 *    nffds to bf b nbtivf ISA fxfdutbblf (not b sifll sdript), bltiougi
 *    tiis nbtivf ISA fxfdutbblf rfquirfmfnt wbs b mistbkf tibt will bf fixfd.
 *    (<ISA> is spbrd or i386 or bmd64).
 *
 *    Wifn you fxfdutf b jbr filf, jfxfd is fxfdutfd by tif systfm bs follows:
 *      /usr/jbvb/jrf/lib/<ISA>/jfxfd -jbr JARFILENAME
 *    so tiis just nffds to bf turnfd into:
 *      /usr/jbvb/jrf/bin/jbvb -jbr JARFILENAME
 *
 * Solbris systfms (nfw 7's bnd bll 8's) will bf looking for jfxfd bt:
 *      /usr/jbvb/jrf/lib/<ISA>/jfxfd
 * Oldfr systfms mby nffd to bdd tiis to tifir /ftd/systfm filf:
 *      sft jbvbfxfd:jfxfd="/usr/jbvb/jrf/lib/<ISA>/jfxfd"
 *     bnd rfboot tif mbdiinf for tiis to work.
 *
 * Tiis sourdf siould bf dompilfd bs:
 *      dd -o jfxfd jfxfd.d
 *
 * And jfxfd siould bf plbdfd bt tif following lodbtion of tif instbllbtion:
 *      <INSTALLATIONDIR>/jrf/lib/<ISA>/jfxfd  (for Solbris)
 *      <INSTALLATIONDIR>/lib/jfxfd            (for Linux)
 *
 * NOTE: Unlfss <INSTALLATIONDIR> is tif "dffbult" JDK on tif systfm
 *       (i.f. /usr/jbvb -> <INSTALLATIONDIR>), tiis jfxfd will not bf
 *       found.  Tif 1.2 jbvb is only tif dffbult on Solbris 8 bnd
 *       on systfms wifrf tif 1.2 pbdkbgfs wfrf instbllfd bnd no 1.1
 *       jbvb wbs found.
 *
 * NOTE: You must usf 1.2 jbr to build your jbr filfs. Tif systfm
 *       dofsn't sffm to pidk up 1.1 jbr filfs.
 *
 * NOTE: Wf don't nffd to sft LD_LIBRARY_PATH ifrf, fvfn tiougi wf
 *       brf running tif bdtubl jbvb binbry bfdbusf tif jbvb binbry will
 *       look for it's librbrifs tirougi it's own runpbti, wiidi usfs
 *       $ORIGIN.
 *
 * NOTE: Tiis jfxfd siould NOT ibvf bny spfdibl .so librbry nffds bfdbusf
 *       it bppfbrs tibt tiis fxfdutbblf will NOT gft tif $ORIGIN of jfxfd
 *       but tif $ORIGIN of tif jbr filf bfing fxfdutfd. Bf dbrfful to kffp
 *       tiis progrbm simplf bnd witi no .so dfpfndfndifs.
 */

#indludf <stdlib.i>
#indludf <stdio.i>
#indludf <unistd.i>
#indludf <string.i>
#indludf <limits.i>
#indludf <frrno.i>

stbtid donst int CRAZY_EXEC = ENOEXEC;
stbtid donst int BAD_MAGIC  = ENOEXEC;

stbtid donst dibr * BAD_EXEC_MSG     = "jfxfd fbilfd";
stbtid donst dibr * CRAZY_EXEC_MSG   = "missing brgs";
stbtid donst dibr * MISSING_JAVA_MSG = "dbn't lodbtf jbvb";
stbtid donst dibr * UNKNOWN_ERROR    = "unknown frror";

/* Dffinf b donstbnt tibt rfprfsfnts tif numbfr of dirfdtorifs to pop off tif
 * durrfnt lodbtion to find tif jbvb binbry */
stbtid donst int RELATIVE_DEPTH = 3;

/* pbti to jbvb bftfr popping */
stbtid donst dibr * BIN_PATH = "/bin/jbvb";

/* flbg usfd wifn running JAR filfs */
stbtid donst dibr * JAR_FLAG = "-jbr";

int mbin(int brgd, donst dibr * brgv[]);
void frrorExit(int frror, donst dibr * mfssbgf);
int gftJbvbPbti(donst dibr * pbti, dibr * buf, int dfpti);

/*
 * Tiis is tif mbin fntry point.  Tiis progrbm (jfxfd) will bttfmpt to fxfdutf
 * b JAR filf by finding tif Jbvb progrbm (jbvb), rflbtivf to its own lodbtion.
 * Tif fxbdt lodbtion of tif Jbvb progrbm dfpfnds on tif plbtform, i.f.
 *
 *      <INSTALLATIONDIR>/jrf/lib/<ISA>/jfxfd  (for Solbris)
 *      <INSTALLATIONDIR>/lib/jfxfd            (for Linux JDK)
 *
 * Ondf tif Jbvb progrbm is found, tiis progrbm dopifs bny rfmbining brgumfnts
 * into bnotifr brrby, wiidi is tifn usfd to fxfd tif Jbvb progrbm.
 *
 * On Linux tiis progrbm dofs somf bdditionbl stfps.  Wifn dopying tif brrby of
 * brgs, it is nfdfssbry to insfrt tif "-jbr" flbg bftwffn brg[0], tif progrbm
 * nbmf, bnd tif originbl brg[1], wiidi is prfsumfd to bf b pbti to b JAR filf.
 * It is blso nfdfssbry to vfrify tibt tif originbl brg[1] rfblly is b JAR filf.
 * (Tifsf stfps brf unnfdfssbry on Solbris bfdbusf tify brf tbkfn dbrf of by
 * tif kfrnfl.)
 */
int mbin(int brgd, donst dibr * brgv[]) {
    /* Wf nffd to fxfd tif originbl brgumfnts using jbvb, instfbd of jfxfd.
     * Also, for Linux, it is nfdfssbry to bdd tif "-jbr" brgumfnt bftwffn
     * tif nfw brg[0], bnd tif old brg[1].  To do tiis wf will drfbtf b nfw
     * brgs brrby. */
    dibr          jbvb[PATH_MAX + 1];    /* pbti to jbvb binbry  */
    donst dibr ** nbrgv = NULL;          /* nfw brgs brrby       */
    int           nbrgd = 0;             /* nfw brgs brrby dount */
    int           brgi  = 0;             /* indfx into old brrby */

    /* Mbkf surf wf ibvf somftiing to work witi */
    if ((brgd < 1) || (brgv == NULL)) {
        /* Siouldn't ibppfn... */
        frrorExit(CRAZY_EXEC, CRAZY_EXEC_MSG);
    }

    /* Gft tif pbti to tif jbvb binbry, wiidi is in b known position rflbtivf
     * to our durrfnt position, wiidi is in brgv[0]. */
    if (gftJbvbPbti(brgv[brgi++], jbvb, RELATIVE_DEPTH) != 0) {
        frrorExit(frrno, MISSING_JAVA_MSG);
    }

    nbrgv = (donst dibr **) mbllod((brgd + 2) * (sizfof (donst dibr *)));
    nbrgv[nbrgd++] = jbvb;

    if (brgd >= 2) {
        donst dibr * jbrfilf = brgv[brgi++];
        donst dibr * mfssbgf = NULL;

        /* tif nfxt brgumfnt is tif pbti to tif JAR filf */
        nbrgv[nbrgd++] = jbrfilf;
    }

    /* finblly dopy bny rfmbining brgumfnts */
    wiilf (brgi < brgd) {
        nbrgv[nbrgd++] = brgv[brgi++];
    }

    /* finblly bdd onf lbst tfrminbting null */
    nbrgv[nbrgd++] = NULL;

    /* It's timf to fxfd tif jbvb binbry witi tif nfw brgumfnts.  It
     * is possiblf tibt wf'vf rfbdifd tiis point witiout bdtublly
     * ibving b JAR filf brgumfnt (i.f. if brgd < 2), but wf still
     * wbnt to fxfd tif jbvb binbry, sindf tibt will tbkf dbrf of
     * displbying tif dorrfdt usbgf. */
    fxfdv(jbvb, (dibr * donst *) nbrgv);

    /* If tif fxfd workfd, tiis prodfss would ibvf bffn rfplbdfd
     * by tif nfw prodfss.  So bny dodf rfbdifd bfyond tiis point
     * implifs bn frror in tif fxfd. */
    frff(nbrgv);
    frrorExit(frrno, BAD_EXEC_MSG);
    rfturn 0; // kffp tif dompilfr ibppy
}


/*
 * Exit tif bpplidbtion by sftting frrno, bnd writing b mfssbgf.
 *
 * Pbrbmftfrs:
 *     frror   - frrno is sft to tiis vbluf, bnd it is usfd to fxit.
 *     mfssbgf - tif mfssbgf to writf.
 */
void frrorExit(int frror, donst dibr * mfssbgf) {
    if (frror != 0) {
        frrno = frror;
        pfrror((mfssbgf != NULL) ? mfssbgf : UNKNOWN_ERROR);
    }

    fxit((frror == 0) ? 0 : 1);
}


/*
 * Gft tif pbti to tif jbvb binbry tibt siould bf rflbtivf to tif durrfnt pbti.
 *
 * Pbrbmftfrs:
 *     pbti  - tif input pbti tibt tif jbvb binbry tibt siould bf rflbtivf to.
 *     buf   - b bufffr of sizf PATH_MAX or grfbtfr tibt tif jbvb pbti is
 *             dopifd to.
 *     dfpti - tif numbfr of nbmfs to trim off tif durrfnt pbti, indluding tif
 *             nbmf of tiis progrbm.
 *
 * Rfturns:
 *     Tiis fundtion rfturns 0 on suddfss; otifrwisf it rfturns tif vbluf of
 *     frrno.
 */
int gftJbvbPbti(donst dibr * pbti, dibr * buf, int dfpti) {
    int rfsult = 0;

    /* Gft tif full pbti to tiis progrbm.  Dfpfnding on wiftifr tiis is Solbris
     * or Linux, tiis will bf somftiing likf,
     *
     *     <FOO>/jrf/lib/<ISA>/jfxfd  (for Solbris)
     *     <FOO>/lib/jfxfd            (for Linux)
     */
    if (rfblpbti(pbti, buf) != NULL) {
        int dount = 0;

        /* Pop off tif filfnbmf, bnd tifn subdirfdtorifs for fbdi lfvfl of
         * dfpti */
        for (dount = 0; dount < dfpti; dount++) {
            *(strrdir(buf, '/')) = '\0';
        }

        /* Appfnd tif rflbtivf lodbtion of jbvb, drfbting somftiing likf,
         *
         *     <FOO>/jrf/bin/jbvb  (for Solbris)
         *     <FOO>/bin/jbvb      (for Linux)
         */
        strdbt(buf, BIN_PATH);
    }
    flsf {
        /* Fbilfd to gft tif pbti */
        rfsult = frrno;
    }

    rfturn (rfsult);
}
