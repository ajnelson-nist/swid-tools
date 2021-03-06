/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government and is
 * being made available as a public service. Pursuant to title 17 United States
 * Code Section 105, works of NIST employees are not subject to copyright
 * protection in the United States. This software may be subject to foreign
 * copyright. Permission in the United States and in foreign countries, to the
 * extent that NIST may hold copyright, to use, copy, modify, create derivative
 * works, and distribute this software and its documentation without fee is hereby
 * granted on a non-exclusive basis, provided that this notice and disclaimer
 * of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE.  IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM,
 * OR IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.secauto.swid.builder;

public enum KnownVersionScheme implements VersionScheme {
  /**
   * Numbers separated by dots, where the numbers are interpreted as integers (e.g., 1.2.3, 1.4.5.6,
   * 1.2.3.4.5.6.7).
   */
  MULTIPART_NUMERIC(1, "multipartnumeric"),
  /**
   * Numbers separated by dots, where the numbers are interpreted as integers with an additional
   * string suffix: (e.g., 1.2.3a).
   */
  MULTIPART_NUMERIC_WITH_SUFFIX(2, "multipartnumeric+suffix"),
  /**
   * an alpha-numeric string, that can be sorted based on alpha-numeric order.
   */
  ALPHANUMERIC(3, "alphanumeric"),
  /**
   * A decimal number (e.g., 1.25 is less than 1.3 ).
   */
  DECIMAL(4, "decimal"),
  /**
   * Follows the <a href="http://semver.org/">Semantic Versioning</a> specificatio.
   */
  SEMVER(16384, "semver");

  private final int index;
  private final String name;

  private KnownVersionScheme(int index, String name) {
    this.index = index;
    this.name = name;
    init(index, name);
  }

  /**
   * Get the integer index value for the version scheme.
   * 
   * @return the index
   */
  @Override
  public Integer getIndex() {
    return index;
  }

  /**
   * Get the human-readable text value for the version scheme.
   * 
   * @return the value
   */
  @Override
  public String getName() {
    return name;
  }

}
