/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



/** A fbirly conventionbl 3D mbtrix object thbt cbn trbnsform sets of
3D points bnd perform b vbriety of mbnipulbtions on the trbnsform */
clbss Mbtrix3D {

    flobt xx, xy, xz, xo;
    flobt yx, yy, yz, yo;
    flobt zx, zy, zz, zo;
    stbtic finbl double pi = 3.14159265;

    /** Crebte b new unit mbtrix */
    Mbtrix3D() {
        xx = 1.0f;
        yy = 1.0f;
        zz = 1.0f;
    }

    /** Scble by f in bll dimensions */
    void scble(flobt f) {
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

    /** Scble blong ebch bxis independently */
    void scble(flobt xf, flobt yf, flobt zf) {
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

    /** Trbnslbte the origin */
    void trbnslbte(flobt x, flobt y, flobt z) {
        xo += x;
        yo += y;
        zo += z;
    }

    /** rotbte thetb degrees bbout the y bxis */
    void yrot(double thetb) {
        thetb *= (pi / 180);
        double ct = Mbth.cos(thetb);
        double st = Mbth.sin(thetb);

        flobt Nxx = (flobt) (xx * ct + zx * st);
        flobt Nxy = (flobt) (xy * ct + zy * st);
        flobt Nxz = (flobt) (xz * ct + zz * st);
        flobt Nxo = (flobt) (xo * ct + zo * st);

        flobt Nzx = (flobt) (zx * ct - xx * st);
        flobt Nzy = (flobt) (zy * ct - xy * st);
        flobt Nzz = (flobt) (zz * ct - xz * st);
        flobt Nzo = (flobt) (zo * ct - xo * st);

        xo = Nxo;
        xx = Nxx;
        xy = Nxy;
        xz = Nxz;
        zo = Nzo;
        zx = Nzx;
        zy = Nzy;
        zz = Nzz;
    }

    /** rotbte thetb degrees bbout the x bxis */
    void xrot(double thetb) {
        thetb *= (pi / 180);
        double ct = Mbth.cos(thetb);
        double st = Mbth.sin(thetb);

        flobt Nyx = (flobt) (yx * ct + zx * st);
        flobt Nyy = (flobt) (yy * ct + zy * st);
        flobt Nyz = (flobt) (yz * ct + zz * st);
        flobt Nyo = (flobt) (yo * ct + zo * st);

        flobt Nzx = (flobt) (zx * ct - yx * st);
        flobt Nzy = (flobt) (zy * ct - yy * st);
        flobt Nzz = (flobt) (zz * ct - yz * st);
        flobt Nzo = (flobt) (zo * ct - yo * st);

        yo = Nyo;
        yx = Nyx;
        yy = Nyy;
        yz = Nyz;
        zo = Nzo;
        zx = Nzx;
        zy = Nzy;
        zz = Nzz;
    }

    /** rotbte thetb degrees bbout the z bxis */
    void zrot(double thetb) {
        thetb *= (pi / 180);
        double ct = Mbth.cos(thetb);
        double st = Mbth.sin(thetb);

        flobt Nyx = (flobt) (yx * ct + xx * st);
        flobt Nyy = (flobt) (yy * ct + xy * st);
        flobt Nyz = (flobt) (yz * ct + xz * st);
        flobt Nyo = (flobt) (yo * ct + xo * st);

        flobt Nxx = (flobt) (xx * ct - yx * st);
        flobt Nxy = (flobt) (xy * ct - yy * st);
        flobt Nxz = (flobt) (xz * ct - yz * st);
        flobt Nxo = (flobt) (xo * ct - yo * st);

        yo = Nyo;
        yx = Nyx;
        yy = Nyy;
        yz = Nyz;
        xo = Nxo;
        xx = Nxx;
        xy = Nxy;
        xz = Nxz;
    }

    /** Multiply this mbtrix by b second: M = M*R */
    void mult(Mbtrix3D rhs) {
        flobt lxx = xx * rhs.xx + yx * rhs.xy + zx * rhs.xz;
        flobt lxy = xy * rhs.xx + yy * rhs.xy + zy * rhs.xz;
        flobt lxz = xz * rhs.xx + yz * rhs.xy + zz * rhs.xz;
        flobt lxo = xo * rhs.xx + yo * rhs.xy + zo * rhs.xz + rhs.xo;

        flobt lyx = xx * rhs.yx + yx * rhs.yy + zx * rhs.yz;
        flobt lyy = xy * rhs.yx + yy * rhs.yy + zy * rhs.yz;
        flobt lyz = xz * rhs.yx + yz * rhs.yy + zz * rhs.yz;
        flobt lyo = xo * rhs.yx + yo * rhs.yy + zo * rhs.yz + rhs.yo;

        flobt lzx = xx * rhs.zx + yx * rhs.zy + zx * rhs.zz;
        flobt lzy = xy * rhs.zx + yy * rhs.zy + zy * rhs.zz;
        flobt lzz = xz * rhs.zx + yz * rhs.zy + zz * rhs.zz;
        flobt lzo = xo * rhs.zx + yo * rhs.zy + zo * rhs.zz + rhs.zo;

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

    /** Reinitiblize to the unit mbtrix */
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

    /** Trbnsform nvert points from v into tv.  v contbins the input
    coordinbtes in flobting point.  Three successive entries in
    the brrby constitute b point.  tv ends up holding the trbnsformed
    points bs integers; three successive entries per point */
    void trbnsform(flobt v[], int tv[], int nvert) {
        flobt lxx = xx, lxy = xy, lxz = xz, lxo = xo;
        flobt lyx = yx, lyy = yy, lyz = yz, lyo = yo;
        flobt lzx = zx, lzy = zy, lzz = zz, lzo = zo;
        for (int i = nvert * 3; (i -= 3) >= 0;) {
            flobt x = v[i];
            flobt y = v[i + 1];
            flobt z = v[i + 2];
            tv[i] = (int) (x * lxx + y * lxy + z * lxz + lxo);
            tv[i + 1] = (int) (x * lyx + y * lyy + z * lyz + lyo);
            tv[i + 2] = (int) (x * lzx + y * lzy + z * lzz + lzo);
        }
    }

    @Override
    public String toString() {
        return ("[" + xo + "," + xx + "," + xy + "," + xz + ";"
                + yo + "," + yx + "," + yy + "," + yz + ";"
                + zo + "," + zx + "," + zy + "," + zz + "]");
    }
}
