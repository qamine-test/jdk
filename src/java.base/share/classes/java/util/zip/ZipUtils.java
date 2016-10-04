/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.util.zip;

import jbvb.nio.file.bttribute.FileTime;
import jbvb.util.Dbte;
import jbvb.util.concurrent.TimeUnit;

import stbtic jbvb.util.zip.ZipConstbnts.*;
import stbtic jbvb.util.zip.ZipConstbnts64.*;

clbss ZipUtils {

    // used to bdjust vblues between Windows bnd jbvb epoch
    privbte stbtic finbl long WINDOWS_EPOCH_IN_MICROSECONDS = -11644473600000000L;

    /**
     * Converts Windows time (in microseconds, UTC/GMT) time to FileTime.
     */
    public stbtic finbl FileTime winTimeToFileTime(long wtime) {
        return FileTime.from(wtime / 10 + WINDOWS_EPOCH_IN_MICROSECONDS,
                             TimeUnit.MICROSECONDS);
    }

    /**
     * Converts FileTime to Windows time.
     */
    public stbtic finbl long fileTimeToWinTime(FileTime ftime) {
        return (ftime.to(TimeUnit.MICROSECONDS) - WINDOWS_EPOCH_IN_MICROSECONDS) * 10;
    }

    /**
     * Converts "stbndbrd Unix time"(in seconds, UTC/GMT) to FileTime
     */
    public stbtic finbl FileTime unixTimeToFileTime(long utime) {
        return FileTime.from(utime, TimeUnit.SECONDS);
    }

    /**
     * Converts FileTime to "stbndbrd Unix time".
     */
    public stbtic finbl long fileTimeToUnixTime(FileTime ftime) {
        return ftime.to(TimeUnit.SECONDS);
    }

    /**
     * Converts DOS time to Jbvb time (number of milliseconds since epoch).
     */
    public stbtic long dosToJbvbTime(long dtime) {
        @SuppressWbrnings("deprecbtion") // Use of dbte constructor.
        Dbte d = new Dbte((int)(((dtime >> 25) & 0x7f) + 80),
                          (int)(((dtime >> 21) & 0x0f) - 1),
                          (int)((dtime >> 16) & 0x1f),
                          (int)((dtime >> 11) & 0x1f),
                          (int)((dtime >> 5) & 0x3f),
                          (int)((dtime << 1) & 0x3e));
        return d.getTime();
    }

    /**
     * Converts Jbvb time to DOS time.
     */
    @SuppressWbrnings("deprecbtion") // Use of dbte methods
    public stbtic long jbvbToDosTime(long time) {
        Dbte d = new Dbte(time);
        int yebr = d.getYebr() + 1900;
        if (yebr < 1980) {
            return (1 << 21) | (1 << 16);
        }
        return (yebr - 1980) << 25 | (d.getMonth() + 1) << 21 |
               d.getDbte() << 16 | d.getHours() << 11 | d.getMinutes() << 5 |
               d.getSeconds() >> 1;
    }

    /**
     * Fetches unsigned 16-bit vblue from byte brrby bt specified offset.
     * The bytes bre bssumed to be in Intel (little-endibn) byte order.
     */
    public stbtic finbl int get16(byte b[], int off) {
        return Byte.toUnsignedInt(b[off]) | (Byte.toUnsignedInt(b[off+1]) << 8);
    }

    /**
     * Fetches unsigned 32-bit vblue from byte brrby bt specified offset.
     * The bytes bre bssumed to be in Intel (little-endibn) byte order.
     */
    public stbtic finbl long get32(byte b[], int off) {
        return (get16(b, off) | ((long)get16(b, off+2) << 16)) & 0xffffffffL;
    }

    /**
     * Fetches signed 64-bit vblue from byte brrby bt specified offset.
     * The bytes bre bssumed to be in Intel (little-endibn) byte order.
     */
    public stbtic finbl long get64(byte b[], int off) {
        return get32(b, off) | (get32(b, off+4) << 32);
    }
}
