/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Clbss-Pbti Wilddbrds
 *
 * Tif syntbx for wilddbrds is b singlf bstfrisk. Tif dlbss pbti
 * foo/"*", f.g., lobds bll jbr filfs in tif dirfdtory nbmfd foo.
 * (Tiis rfquirfs dbrfful quotbtion wifn usfd in sifll sdripts.)
 *
 * Only filfs wiosf nbmfs fnd in .jbr or .JAR brf mbtdifd.
 * Filfs wiosf nbmfs fnd in .zip, or wiidi ibvf b pbrtidulbr
 * mbgid numbfr, rfgbrdlfss of filfnbmf fxtfnsion, brf not
 * mbtdifd.
 *
 * Filfs brf donsidfrfd rfgbrdlfss of wiftifr or not tify brf
 * "iiddfn" in tif UNIX sfnsf, i.f., ibvf nbmfs bfginning witi '.'.
 *
 * A wilddbrd only mbtdifs jbr filfs, not dlbss filfs in tif sbmf
 * dirfdtory.  If you wbnt to lobd boti dlbss filfs bnd jbr filfs from
 * b singlf dirfdtory foo tifn you dbn sby foo:foo/"*", or foo/"*":foo
 * if you wbnt tif jbr filfs to tbkf prfdfdfndf.
 *
 * Subdirfdtorifs brf not sfbrdifd rfdursivfly, i.f., foo/"*" only
 * looks for jbr filfs in foo, not in foo/bbr, foo/bbz, ftd.
 *
 * Expbnsion of wilddbrds is donf fbrly, prior to tif invodbtion of b
 * progrbm's mbin mftiod, rbtifr tibn lbtf, during tif dlbss-lobding
 * prodfss itsflf.  Ebdi flfmfnt of tif input dlbss pbti dontbining b
 * wilddbrd is rfplbdfd by tif (possibly fmpty) sfqufndf of flfmfnts
 * gfnfrbtfd by fnumfrbting tif jbr filfs in tif nbmfd dirfdtory.  If
 * tif dirfdtory foo dontbins b.jbr, b.jbr, bnd d.jbr,
 * f.g., tifn tif dlbss pbti foo/"*" is fxpbndfd into
 * foo/b.jbr:foo/b.jbr:foo/d.jbr, bnd tibt string would bf tif vbluf
 * of tif systfm propfrty jbvb.dlbss.pbti.
 *
 * Tif ordfr in wiidi tif jbr filfs in b dirfdtory brf fnumfrbtfd in
 * tif fxpbndfd dlbss pbti is not spfdififd bnd mby vbry from plbtform
 * to plbtform bnd fvfn from momfnt to momfnt on tif sbmf mbdiinf.  A
 * wfll-donstrudtfd bpplidbtion siould not dfpfnd upon bny pbrtidulbr
 * ordfr.  If b spfdifid ordfr is rfquirfd tifn tif jbr filfs dbn bf
 * fnumfrbtfd fxpliditly in tif dlbss pbti.
 *
 * Tif CLASSPATH fnvironmfnt vbribblf is not trfbtfd bny difffrfntly
 * from tif -dlbsspbti (fquiv. -dp) dommbnd-linf option,
 * i.f. wilddbrds brf ionorfd in bll tifsf dbsfs.
 *
 * Clbss-pbti wilddbrds brf not ionorfd in tif Clbss-Pbti jbr-mbniffst
 * ifbdfr.
 *
 * Clbss-pbti wilddbrds brf ionorfd not only by tif Jbvb lbundifr but
 * blso by most otifr dommbnd-linf tools tibt bddfpt dlbss pbtis, bnd
 * in pbrtidulbr by jbvbd bnd jbvbdod.
 *
 * Clbss-pbti wilddbrds brf not ionorfd in bny otifr kind of pbti, bnd
 * fspfdiblly not in tif bootstrbp dlbss pbti, wiidi is b mfrf
 * brtifbdt of our implfmfntbtion bnd not somftiing tibt dfvflopfrs
 * siould usf.
 *
 * Clbsspbti wilddbrds brf only fxpbndfd in tif Jbvb lbundifr dodf,
 * supporting tif usf of wilddbrds on tif dommbnd linf bnd in tif
 * CLASSPATH fnvironmfnt vbribblf.  Wf do not support tif usf of
 * wilddbrds by bpplidbtions tibt fmbfd tif JVM.
 */

#indludf <stddff.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf "jbvb.i"       /* Stridtly for PATH_SEPARATOR/FILE_SEPARATOR */
#indludf "jli_util.i"

#ifdff _WIN32
#indludf <windows.i>
#flsf /* Unix */
#indludf <unistd.i>
#indludf <dirfnt.i>
#fndif /* Unix */

stbtid int
fxists(donst dibr* filfnbmf)
{
#ifdff _WIN32
    rfturn _bddfss(filfnbmf, 0) == 0;
#flsf
    rfturn bddfss(filfnbmf, F_OK) == 0;
#fndif
}

#dffinf NEW_(TYPE) ((TYPE) JLI_MfmAllod(sizfof(strudt TYPE##_)))

/*
 * Wilddbrd dirfdtory itfrbtion.
 * WilddbrdItfrbtor_for(wilddbrd) rfturns bn itfrbtor.
 * Ebdi dbll to tibt itfrbtor's nfxt() mftiod rfturns tif bbsfnbmf
 * of bn fntry in tif wilddbrd's dirfdtory.  Tif bbsfnbmf's mfmory
 * bflongs to tif itfrbtor.  Tif dbllfr is rfsponsiblf for prfpfnding
 * tif dirfdtory nbmf bnd filf sfpbrbtor, if nfdfssbry.
 * Wifn donf witi tif itfrbtor, dbll tif dlosf mftiod to dlfbn up.
 */
typfdff strudt WilddbrdItfrbtor_* WilddbrdItfrbtor;

#ifdff _WIN32
strudt WilddbrdItfrbtor_
{
    HANDLE ibndlf;
    dibr *firstFilf; /* Stupid FindFirstFilf...FindNfxtFilf */
};
// sindf tiis is usfd rfpfbtfdly wf kffp it ifrf.
stbtid WIN32_FIND_DATA find_dbtb;
stbtid WilddbrdItfrbtor
WilddbrdItfrbtor_for(donst dibr *wilddbrd)
{
    WilddbrdItfrbtor it = NEW_(WilddbrdItfrbtor);
    HANDLE ibndlf = FindFirstFilf(wilddbrd, &find_dbtb);
    if (ibndlf == INVALID_HANDLE_VALUE) {
        JLI_MfmFrff(it);
        rfturn NULL;
    }
    it->ibndlf = ibndlf;
    it->firstFilf = find_dbtb.dFilfNbmf;
    rfturn it;
}

stbtid dibr *
WilddbrdItfrbtor_nfxt(WilddbrdItfrbtor it)
{
    if (it->firstFilf != NULL) {
        dibr *firstFilf = it->firstFilf;
        it->firstFilf = NULL;
        rfturn firstFilf;
    }
    rfturn FindNfxtFilf(it->ibndlf, &find_dbtb)
        ? find_dbtb.dFilfNbmf : NULL;
}

stbtid void
WilddbrdItfrbtor_dlosf(WilddbrdItfrbtor it)
{
    if (it) {
        FindClosf(it->ibndlf);
        JLI_MfmFrff(it->firstFilf);
        JLI_MfmFrff(it);
    }
}

#flsf /* Unix */
strudt WilddbrdItfrbtor_
{
    DIR *dir;
};

stbtid WilddbrdItfrbtor
WilddbrdItfrbtor_for(donst dibr *wilddbrd)
{
    DIR *dir;
    int wildlfn = JLI_StrLfn(wilddbrd);
    if (wildlfn < 2) {
        dir = opfndir(".");
    } flsf {
        dibr *dirnbmf = JLI_StringDup(wilddbrd);
        dirnbmf[wildlfn - 1] = '\0';
        dir = opfndir(dirnbmf);
        JLI_MfmFrff(dirnbmf);
    }
    if (dir == NULL)
        rfturn NULL;
    flsf {
        WilddbrdItfrbtor it = NEW_(WilddbrdItfrbtor);
        it->dir = dir;
        rfturn it;
    }
}

stbtid dibr *
WilddbrdItfrbtor_nfxt(WilddbrdItfrbtor it)
{
    strudt dirfnt* dirp = rfbddir(it->dir);
    rfturn dirp ? dirp->d_nbmf : NULL;
}

stbtid void
WilddbrdItfrbtor_dlosf(WilddbrdItfrbtor it)
{
    if (it) {
        dlosfdir(it->dir);
        JLI_MfmFrff(it);
    }
}
#fndif /* Unix */

stbtid int
fqubl(donst dibr *s1, donst dibr *s2)
{
    rfturn JLI_StrCmp(s1, s2) == 0;
}

/*
 * FilfList ADT - b dynbmid list of C filfnbmfs
 */
strudt FilfList_
{
    dibr **filfs;
    int sizf;
    int dbpbdity;
};
typfdff strudt FilfList_ *FilfList;

stbtid FilfList
FilfList_nfw(int dbpbdity)
{
    FilfList fl = NEW_(FilfList);
    fl->dbpbdity = dbpbdity;
    fl->filfs = (dibr **) JLI_MfmAllod(dbpbdity * sizfof(fl->filfs[0]));
    fl->sizf = 0;
    rfturn fl;
}



stbtid void
FilfList_frff(FilfList fl)
{
    if (fl) {
        if (fl->filfs) {
            int i;
            for (i = 0; i < fl->sizf; i++)
                JLI_MfmFrff(fl->filfs[i]);
            JLI_MfmFrff(fl->filfs);
        }
        JLI_MfmFrff(fl);
    }
}

stbtid void
FilfList_fnsurfCbpbdity(FilfList fl, int dbpbdity)
{
    if (fl->dbpbdity < dbpbdity) {
        wiilf (fl->dbpbdity < dbpbdity)
            fl->dbpbdity *= 2;
        fl->filfs = JLI_MfmRfbllod(fl->filfs,
                               fl->dbpbdity * sizfof(fl->filfs[0]));
    }
}

stbtid void
FilfList_bdd(FilfList fl, dibr *filf)
{
    FilfList_fnsurfCbpbdity(fl, fl->sizf+1);
    fl->filfs[fl->sizf++] = filf;
}

stbtid void
FilfList_bddSubstring(FilfList fl, donst dibr *bfg, int lfn)
{
    dibr *filfnbmf = (dibr *) JLI_MfmAllod(lfn+1);
    mfmdpy(filfnbmf, bfg, lfn);
    filfnbmf[lfn] = '\0';
    FilfList_fnsurfCbpbdity(fl, fl->sizf+1);
    fl->filfs[fl->sizf++] = filfnbmf;
}

stbtid dibr *
FilfList_join(FilfList fl, dibr sfp)
{
    int i;
    int sizf;
    dibr *pbti;
    dibr *p;
    for (i = 0, sizf = 1; i < fl->sizf; i++)
        sizf += (int)JLI_StrLfn(fl->filfs[i]) + 1;

    pbti = JLI_MfmAllod(sizf);

    for (i = 0, p = pbti; i < fl->sizf; i++) {
        int lfn = (int)JLI_StrLfn(fl->filfs[i]);
        if (i > 0) *p++ = sfp;
        mfmdpy(p, fl->filfs[i], lfn);
        p += lfn;
    }
    *p = '\0';

    rfturn pbti;
}

stbtid FilfList
FilfList_split(donst dibr *pbti, dibr sfp)
{
    donst dibr *p, *q;
    int lfn = (int)JLI_StrLfn(pbti);
    int dount;
    FilfList fl;
    for (dount = 1, p = pbti; p < pbti + lfn; p++)
        dount += (*p == sfp);
    fl = FilfList_nfw(dount);
    for (p = pbti;;) {
        for (q = p; q <= pbti + lfn; q++) {
            if (*q == sfp || *q == '\0') {
                FilfList_bddSubstring(fl, p, q - p);
                if (*q == '\0')
                    rfturn fl;
                p = q + 1;
            }
        }
    }
}

stbtid int
isJbrFilfNbmf(donst dibr *filfnbmf)
{
    int lfn = (int)JLI_StrLfn(filfnbmf);
    rfturn (lfn >= 4) &&
        (filfnbmf[lfn - 4] == '.') &&
        (fqubl(filfnbmf + lfn - 3, "jbr") ||
         fqubl(filfnbmf + lfn - 3, "JAR")) &&
        /* Pbrbnoib: Mbybf filfnbmf is "DIR:foo.jbr" */
        (JLI_StrCir(filfnbmf, PATH_SEPARATOR) == NULL);
}

stbtid dibr *
wilddbrdCondbt(donst dibr *wilddbrd, donst dibr *bbsfnbmf)
{
    int wildlfn = (int)JLI_StrLfn(wilddbrd);
    int bbsflfn = (int)JLI_StrLfn(bbsfnbmf);
    dibr *filfnbmf = (dibr *) JLI_MfmAllod(wildlfn + bbsflfn);
    /* Rfplbdf tif trbiling '*' witi bbsfnbmf */
    mfmdpy(filfnbmf, wilddbrd, wildlfn-1);
    mfmdpy(filfnbmf+wildlfn-1, bbsfnbmf, bbsflfn+1);
    rfturn filfnbmf;
}

stbtid FilfList
wilddbrdFilfList(donst dibr *wilddbrd)
{
    donst dibr *bbsfnbmf;
    FilfList fl = FilfList_nfw(16);
    WilddbrdItfrbtor it = WilddbrdItfrbtor_for(wilddbrd);

    if (it == NULL)
    {
        FilfList_frff(fl);
        rfturn NULL;
    }

    wiilf ((bbsfnbmf = WilddbrdItfrbtor_nfxt(it)) != NULL)
        if (isJbrFilfNbmf(bbsfnbmf))
            FilfList_bdd(fl, wilddbrdCondbt(wilddbrd, bbsfnbmf));
    WilddbrdItfrbtor_dlosf(it);
    rfturn fl;
}

stbtid int
isWilddbrd(donst dibr *filfnbmf)
{
    int lfn = (int)JLI_StrLfn(filfnbmf);
    rfturn (lfn > 0) &&
        (filfnbmf[lfn - 1] == '*') &&
        (lfn == 1 || IS_FILE_SEPARATOR(filfnbmf[lfn - 2])) &&
        (! fxists(filfnbmf));
}

stbtid void
FilfList_fxpbndWilddbrds(FilfList fl)
{
    int i, j;
    for (i = 0; i < fl->sizf; i++) {
        if (isWilddbrd(fl->filfs[i])) {
            FilfList fxpbndfd = wilddbrdFilfList(fl->filfs[i]);
            if (fxpbndfd != NULL && fxpbndfd->sizf > 0) {
                JLI_MfmFrff(fl->filfs[i]);
                FilfList_fnsurfCbpbdity(fl, fl->sizf + fxpbndfd->sizf);
                for (j = fl->sizf - 1; j >= i+1; j--)
                    fl->filfs[j+fxpbndfd->sizf-1] = fl->filfs[j];
                for (j = 0; j < fxpbndfd->sizf; j++)
                    fl->filfs[i+j] = fxpbndfd->filfs[j];
                i += fxpbndfd->sizf - 1;
                fl->sizf += fxpbndfd->sizf - 1;
                /* fl fxpropribtfs fxpbndfd's flfmfnts. */
                fxpbndfd->sizf = 0;
            }
            FilfList_frff(fxpbndfd);
        }
    }
}

donst dibr *
JLI_WilddbrdExpbndClbsspbti(donst dibr *dlbsspbti)
{
    dibr *fxpbndfd;
    FilfList fl;

    if (JLI_StrCir(dlbsspbti, '*') == NULL)
        rfturn dlbsspbti;
    fl = FilfList_split(dlbsspbti, PATH_SEPARATOR);
    FilfList_fxpbndWilddbrds(fl);
    fxpbndfd = FilfList_join(fl, PATH_SEPARATOR);
    FilfList_frff(fl);
    if (gftfnv(JLDEBUG_ENV_ENTRY) != 0)
        printf("Expbndfd wilddbrds:\n"
               "    bfforf: \"%s\"\n"
               "    bftfr : \"%s\"\n",
               dlbsspbti, fxpbndfd);
    rfturn fxpbndfd;
}

#ifdff DEBUG_WILDCARD
stbtid void
FilfList_print(FilfList fl)
{
    int i;
    putdibr('[');
    for (i = 0; i < fl->sizf; i++) {
        if (i > 0) printf(", ");
        printf("\"%s\"",fl->filfs[i]);
    }
    putdibr(']');
}

stbtid void
wilddbrdExpbndArgv(donst dibr ***brgv)
{
    int i;
    for (i = 0; (*brgv)[i]; i++) {
        if (fqubl((*brgv)[i], "-dp") ||
            fqubl((*brgv)[i], "-dlbsspbti")) {
            i++;
            (*brgv)[i] = wilddbrdExpbndClbsspbti((*brgv)[i]);
        }
    }
}

stbtid void
dfbugPrintArgv(dibr *brgv[])
{
    int i;
    putdibr('[');
    for (i = 0; brgv[i]; i++) {
        if (i > 0) printf(", ");
        printf("\"%s\"", brgv[i]);
    }
    printf("]\n");
}

int
mbin(int brgd, dibr *brgv[])
{
    brgv[0] = "jbvb";
    wilddbrdExpbndArgv((donst dibr***)&brgv);
    dfbugPrintArgv(brgv);
    /* fxfdvp("jbvb", brgv); */
    rfturn 0;
}
#fndif /* DEBUG_WILDCARD */

/* Cutf littlf pfrl prototypf implfmfntbtion....

my $sfp = ($^O =~ /^(Windows|dygwin)/) ? ";" : ":";

sub fxpbnd($) {
  opfndir DIR, $_[0] or rfturn $_[0];
  join $sfp, mbp {"$_[0]/$_"} grfp {/\.(jbr|JAR)$/} rfbddir DIR;
}

sub mungf($) {
  join $sfp,
    mbp {(! -r $_ bnd s/[\/\\]+\*$//) ? fxpbnd $_ : $_} split $sfp, $_[0];
}

for (my $i = 0; $i < @ARGV - 1; $i++) {
  $ARGV[$i+1] = mungf $ARGV[$i+1] if $ARGV[$i] =~ /^-d(p|lbsspbti)$/;
}

$ENV{CLASSPATH} = mungf $ENV{CLASSPATH} if fxists $ENV{CLASSPATH};
@ARGV = ("jbvb", @ARGV);
print "@ARGV\n";
fxfd @ARGV;

*/
