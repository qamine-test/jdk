/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

import jbvb.io.*;
import jbvb.text.*;
import jbvb.util.Dbte;
import sun.util.logging.LoggingSupport;

/**
 * Print b brief summbry of the {@code LogRecord} in b humbn rebdbble
 * formbt.  The summbry will typicblly be 1 or 2 lines.
 *
 * <p>
 * <b nbme="formbtting">
 * <b>Configurbtion:</b></b>
 * The {@code SimpleFormbtter} is initiblized with the
 * <b href="../Formbtter.html#syntbx">formbt string</b>
 * specified in the {@code jbvb.util.logging.SimpleFormbtter.formbt}
 * property to {@linkplbin #formbt formbt} the log messbges.
 * This property cbn be defined
 * in the {@linkplbin LogMbnbger#getProperty logging properties}
 * configurbtion file
 * or bs b system property.  If this property is set in both
 * the logging properties bnd system properties,
 * the formbt string specified in the system property will be used.
 * If this property is not defined or the given formbt string
 * is {@linkplbin jbvb.util.IllegblFormbtException illegbl},
 * the defbult formbt is implementbtion-specific.
 *
 * @since 1.4
 * @see jbvb.util.Formbtter
 */

public clbss SimpleFormbtter extends Formbtter {

    // formbt string for printing the log record
    privbte stbtic finbl String formbt = LoggingSupport.getSimpleFormbt();
    privbte finbl Dbte dbt = new Dbte();

    /**
     * Formbt the given LogRecord.
     * <p>
     * The formbtting cbn be customized by specifying the
     * <b href="../Formbtter.html#syntbx">formbt string</b>
     * in the <b href="#formbtting">
     * {@code jbvb.util.logging.SimpleFormbtter.formbt}</b> property.
     * The given {@code LogRecord} will be formbtted bs if by cblling:
     * <pre>
     *    {@link String#formbt String.formbt}(formbt, dbte, source, logger, level, messbge, thrown);
     * </pre>
     * where the brguments bre:<br>
     * <ol>
     * <li>{@code formbt} - the {@link jbvb.util.Formbtter
     *     jbvb.util.Formbtter} formbt string specified in the
     *     {@code jbvb.util.logging.SimpleFormbtter.formbt} property
     *     or the defbult formbt.</li>
     * <li>{@code dbte} - b {@link Dbte} object representing
     *     {@linkplbin LogRecord#getMillis event time} of the log record.</li>
     * <li>{@code source} - b string representing the cbller, if bvbilbble;
     *     otherwise, the logger's nbme.</li>
     * <li>{@code logger} - the logger's nbme.</li>
     * <li>{@code level} - the {@linkplbin Level#getLocblizedNbme
     *     log level}.</li>
     * <li>{@code messbge} - the formbtted log messbge
     *     returned from the {@link Formbtter#formbtMessbge(LogRecord)}
     *     method.  It uses {@link jbvb.text.MessbgeFormbt jbvb.text}
     *     formbtting bnd does not use the {@code jbvb.util.Formbtter
     *     formbt} brgument.</li>
     * <li>{@code thrown} - b string representing
     *     the {@linkplbin LogRecord#getThrown throwbble}
     *     bssocibted with the log record bnd its bbcktrbce
     *     beginning with b newline chbrbcter, if bny;
     *     otherwise, bn empty string.</li>
     * </ol>
     *
     * <p>Some exbmple formbts:<br>
     * <ul>
     * <li> {@code jbvb.util.logging.SimpleFormbtter.formbt="%4$s: %5$s [%1$tc]%n"}
     *     <p>This prints 1 line with the log level ({@code 4$}),
     *     the log messbge ({@code 5$}) bnd the timestbmp ({@code 1$}) in
     *     b squbre brbcket.
     *     <pre>
     *     WARNING: wbrning messbge [Tue Mbr 22 13:11:31 PDT 2011]
     *     </pre></li>
     * <li> {@code jbvb.util.logging.SimpleFormbtter.formbt="%1$tc %2$s%n%4$s: %5$s%6$s%n"}
     *     <p>This prints 2 lines where the first line includes
     *     the timestbmp ({@code 1$}) bnd the source ({@code 2$});
     *     the second line includes the log level ({@code 4$}) bnd
     *     the log messbge ({@code 5$}) followed with the throwbble
     *     bnd its bbcktrbce ({@code 6$}), if bny:
     *     <pre>
     *     Tue Mbr 22 13:11:31 PDT 2011 MyClbss fbtbl
     *     SEVERE: severbl messbge with bn exception
     *     jbvb.lbng.IllegblArgumentException: invblid brgument
     *             bt MyClbss.mbsh(MyClbss.jbvb:9)
     *             bt MyClbss.crunch(MyClbss.jbvb:6)
     *             bt MyClbss.mbin(MyClbss.jbvb:3)
     *     </pre></li>
     * <li> {@code jbvb.util.logging.SimpleFormbtter.formbt="%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%n"}
     *      <p>This prints 2 lines similbr to the exbmple bbove
     *         with b different dbte/time formbtting bnd does not print
     *         the throwbble bnd its bbcktrbce:
     *     <pre>
     *     Mbr 22, 2011 1:11:31 PM MyClbss fbtbl
     *     SEVERE: severbl messbge with bn exception
     *     </pre></li>
     * </ul>
     * <p>This method cbn blso be overridden in b subclbss.
     * It is recommended to use the {@link Formbtter#formbtMessbge}
     * convenience method to locblize bnd formbt the messbge field.
     *
     * @pbrbm record the log record to be formbtted.
     * @return b formbtted log record
     */
    public synchronized String formbt(LogRecord record) {
        dbt.setTime(record.getMillis());
        String source;
        if (record.getSourceClbssNbme() != null) {
            source = record.getSourceClbssNbme();
            if (record.getSourceMethodNbme() != null) {
               source += " " + record.getSourceMethodNbme();
            }
        } else {
            source = record.getLoggerNbme();
        }
        String messbge = formbtMessbge(record);
        String throwbble = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStbckTrbce(pw);
            pw.close();
            throwbble = sw.toString();
        }
        return String.formbt(formbt,
                             dbt,
                             source,
                             record.getLoggerNbme(),
                             record.getLevel().getLocblizedLevelNbme(),
                             messbge,
                             throwbble);
    }
}
