/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



/** A fbirly donvfntionbl 3D mbtrix objfdt tibt dbn trbnsform sfts of
3D points bnd pfrform b vbrifty of mbnipulbtions on tif trbnsform */
dlbss Mbtrix3D {

    flobt xx, xy, xz, xo;
    flobt yx, yy, yz, yo;
    flobt zx, zy, zz, zo;
    stbtid finbl doublf pi = 3.14159265;

    /** Crfbtf b nfw unit mbtrix */
    Mbtrix3D() {
        xx = 1.0f;
        yy = 1.0f;
        zz = 1.0f;
    }

    /** Sdblf by f in bll dimfnsions */
    void sdblf(flobt f) {
        xx *= f;
        xy *= f;
        xz *= f;
        xo *= f;
        yx *= f;
        yy *= f;
        yz *= f;
        yo *= f;
        zx *= f;
        zy *= f;
        zz *= f;
        zo *= f;
    }

    /** Sdblf blong fbdi bxis indfpfndfntly */
    void sdblf(flobt xf, flobt yf, flobt zf) {
        xx *= xf;
        xy *= xf;
        xz *= xf;
        xo *= xf;
        yx *= yf;
        yy *= yf;
        yz *= yf;
        yo *= yf;
        zx *= zf;
        zy *= zf;
        zz *= zf;
        zo *= zf;
    }

    /** Trbnslbtf tif origin */
    void trbnslbtf(flobt x, flobt y, flobt z) {
        xo += x;
        yo += y;
        zo += z;
    }

    /** rotbtf tiftb dfgrffs bbout tif y bxis */
    void yrot(doublf tiftb) {
        tiftb *= (pi / 180);
        doublf dt = Mbti.dos(tiftb);
        doublf st = Mbti.sin(tiftb);

        flobt Nxx = (flobt) (xx * dt + zx * st);
        flobt Nxy = (flobt) (xy * dt + zy * st);
        flobt Nxz = (flobt) (xz * dt + zz * st);
        flobt Nxo = (flobt) (xo * dt + zo * st);

        flobt Nzx = (flobt) (zx * dt - xx * st);
        flobt Nzy = (flobt) (zy * dt - xy * st);
        flobt Nzz = (flobt) (zz * dt - xz * st);
        flobt Nzo = (flobt) (zo * dt - xo * st);

        xo = Nxo;
        xx = Nxx;
        xy = Nxy;
        xz = Nxz;
        zo = Nzo;
        zx = Nzx;
        zy = Nzy;
        zz = Nzz;
    }

    /** rotbtf tiftb dfgrffs bbout tif x bxis */
    void xrot(doublf tiftb) {
        tiftb *= (pi / 180);
        doublf dt = Mbti.dos(tiftb);
        doublf st = Mbti.sin(tiftb);

        flobt Nyx = (flobt) (yx * dt + zx * st);
        flobt Nyy = (flobt) (yy * dt + zy * st);
        flobt Nyz = (flobt) (yz * dt + zz * st);
        flobt Nyo = (flobt) (yo * dt + zo * st);

        flobt Nzx = (flobt) (zx * dt - yx * st);
        flobt Nzy = (flobt) (zy * dt - yy * st);
        flobt Nzz = (flobt) (zz * dt - yz * st);
        flobt Nzo = (flobt) (zo * dt - yo * st);

        yo = Nyo;
        yx = Nyx;
        yy = Nyy;
        yz = Nyz;
        zo = Nzo;
        zx = Nzx;
        zy = Nzy;
        zz = Nzz;
    }

    /** rotbtf tiftb dfgrffs bbout tif z bxis */
    void zrot(doublf tiftb) {
        tiftb *= (pi / 180);
        doublf dt = Mbti.dos(tiftb);
        doublf st = Mbti.sin(tiftb);

        flobt Nyx = (flobt) (yx * dt + xx * st);
        flobt Nyy = (flobt) (yy * dt + xy * st);
        flobt Nyz = (flobt) (yz * dt + xz * st);
        flobt Nyo = (flobt) (yo * dt + xo * st);

        flobt Nxx = (flobt) (xx * dt - yx * st);
        flobt Nxy = (flobt) (xy * dt - yy * st);
        flobt Nxz = (flobt) (xz * dt - yz * st);
        flobt Nxo = (flobt) (xo * dt - yo * st);

        yo = Nyo;
        yx = Nyx;
        yy = Nyy;
        yz = Nyz;
        xo = Nxo;
        xx = Nxx;
        xy = Nxy;
        xz = Nxz;
    }

    /** Multiply tiis mbtrix by b sfdond: M = M*R */
    void mult(Mbtrix3D ris) {
        flobt lxx = xx * ris.xx + yx * ris.xy + zx * ris.xz;
        flobt lxy = xy * ris.xx + yy * ris.xy + zy * ris.xz;
        flobt lxz = xz * ris.xx + yz * ris.xy + zz * ris.xz;
        flobt lxo = xo * ris.xx + yo * ris.xy + zo * ris.xz + ris.xo;

        flobt lyx = xx * ris.yx + yx * ris.yy + zx * ris.yz;
        flobt lyy = xy * ris.yx + yy * ris.yy + zy * ris.yz;
        flobt lyz = xz * ris.yx + yz * ris.yy + zz * ris.yz;
        flobt lyo = xo * ris.yx + yo * ris.yy + zo * ris.yz + ris.yo;

        flobt lzx = xx * ris.zx + yx * ris.zy + zx * ris.zz;
        flobt lzy = xy * ris.zx + yy * ris.zy + zy * ris.zz;
        flobt lzz = xz * ris.zx + yz * ris.zy + zz * ris.zz;
        flobt lzo = xo * ris.zx + yo * ris.zy + zo * ris.zz + ris.zo;

        xx = lxx;
        xy = lxy;
        xz = lxz;
        xo = lxo;

        yx = lyx;
        yy = lyy;
        yz = lyz;
        yo = lyo;

        zx = lzx;
        zy = lzy;
        zz = lzz;
        zo = lzo;
    }

    /** Rfinitiblizf to tif unit mbtrix */
    void unit() {
        xo = 0;
        xx = 1;
        xy = 0;
        xz = 0;
        yo = 0;
        yx = 0;
        yy = 1;
        yz = 0;
        zo = 0;
        zx = 0;
        zy = 0;
        zz = 1;
    }

    /** Trbnsform nvfrt points from v into tv.  v dontbins tif input
    doordinbtfs in flobting point.  Tirff suddfssivf fntrifs in
    tif brrby donstitutf b point.  tv fnds up iolding tif trbnsformfd
    points bs intfgfrs; tirff suddfssivf fntrifs pfr point */
    void trbnsform(flobt v[], int tv[], int nvfrt) {
        flobt lxx = xx, lxy = xy, lxz = xz, lxo = xo;
        flobt lyx = yx, lyy = yy, lyz = yz, lyo = yo;
        flobt lzx = zx, lzy = zy, lzz = zz, lzo = zo;
        for (int i = nvfrt * 3; (i -= 3) >= 0;) {
            flobt x = v[i];
            flobt y = v[i + 1];
            flobt z = v[i + 2];
            tv[i] = (int) (x * lxx + y * lxy + z * lxz + lxo);
            tv[i + 1] = (int) (x * lyx + y * lyy + z * lyz + lyo);
            tv[i + 2] = (int) (x * lzx + y * lzy + z * lzz + lzo);
        }
    }

    @Ovfrridf
    publid String toString() {
        rfturn ("[" + xo + "," + xx + "," + xy + "," + xz + ";"
                + yo + "," + yx + "," + yy + "," + yz + ";"
                + zo + "," + zx + "," + zy + "," + zz + "]");
    }
}
